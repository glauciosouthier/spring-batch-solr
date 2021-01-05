package brs.components.etl.document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import br.com.pix.brs.BRSConnectionPool;
import br.com.pix.brs.BRSDate;
import br.com.pix.brs.BRSException;
import br.com.pix.brs.BRSNormalDate;

public class BrsDocumentRepositoryImpl implements BrsDocumentRepositoryCommon {
	public static final String TODOS_PARAGRAFOS = "ALL";
	public static final String PARAGRAFO_BASE = "BASE";
	public static final String BASE_CONCATENADA = "JURI";
	private static final String NOME_CONEXAO = "unificada";
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private Config config;
	private BRSConnectionPool pool = null;

	public BrsDocumentRepositoryImpl() throws IOException {
		config = Config.instance();
		pool = (new BRSConnectionPool(0, 0, 300000));
	}

	@Override
	public Page<Document> consultaPorQuery(String query, Pageable pageable) throws Exception {
		Long pageSize = (long) pageable.getPageSize();
		// rs comeca em 1
		Long inicio = pageable.getOffset() + 1;
		logger.info("consultaPorQuery(" + query + ", start=" + pageable.getOffset() + ")");
		List<Document> content = new ArrayList<>();
		Long total = 0l;
		br.com.pix.brs.BRSConnection con = getConnection(NOME_CONEXAO);
		try {
			br.com.pix.brs.BRSDatabase db = con.assignDB(config.getBRSDB(NOME_CONEXAO));
			db.setHighlightEnabled(false);
			db.setViewParagraph(TODOS_PARAGRAFOS);
			//
			Long fim = inicio + pageSize;
			Long start = System.currentTimeMillis();
			try {
				br.com.pix.brs.BRSResultSet rs = db.executeSearch(query);
				total = (long) rs.getNumberOfDocuments();
				fim = pageSize == 0 ? total : (inicio + pageSize);
				if (total != 0) {
					List<Document> list = new LinkedList<Document>();
					for (int i = inicio.intValue(); rs.absolute(i - 1) && i < fim; i++) {
						list.add(map(rs, new Document()));
					}
					content = list;
				}
			} finally {
				long elapsedTimeMillis = System.currentTimeMillis() - start;
				logger.debug("Pesquisa : " + query + " [" + inicio + "-" + fim + "] (" + elapsedTimeMillis + " ms)");
			}
		} finally {
			if (con != null)
				con.close();
		}
		return new PageImpl(content, pageable, total);
	}

	/**
	 * Carrega as propriedades da classe Document que foram retornadas pelo BRS.
	 * 
	 * @param rs       Conjuto de resultados retornados do BRS
	 * @param document Objeto da classe Document
	 * @see Document
	 * @exception BRSException
	 * @exception IOException
	 */
	private Document map(br.com.pix.brs.BRSResultSet rs, Document document)
			throws br.com.pix.brs.BRSException, IOException {
		if (Strings.isBlank(rs.getString("id"))) {
			document.setId(rs.getString("docn"));
		} else {
			document.setId(rs.getString("id"));
		}

		document.setDocn(rs.getString("docn"));
		document.setBase(rs.getString("base"));
		// relatoria
		document.setRel(rs.getString("rel"));
		document.setRela(rs.getString("rela"));
		document.setRelc(rs.getString("relc"));
		document.setRev(rs.getString("rev"));
		document.setResp(rs.getString("resp"));
		// datas
		document.setDtpp(rs.getString("dtpp"));
		document.setDtde(rs.getString("dtde"));
		document.setDtdp(rs.getString("dtdp"));
		document.setDtap(rs.getString("dtap"));
		// proc
		document.setPftr(rs.getString("pftr"));
		document.setProc(rs.getString("proc"));
		document.setPrfo(rs.getString("prfo"));

		document.setClas(rs.getString("clas"));
		document.setSigc(rs.getString("sigc"));

		document.setTrib(rs.getString("trib"));
		document.setOrga(rs.getString("orga"));
		document.setOrig(rs.getString("orig"));
		document.setTipo(rs.getString("tipo"));
		document.setUf(rs.getString("uf"));

		// texto do documento
		document.setDeci(rs.getString("deci"));
		document.setEmen(rs.getString("emen"));
		document.setTxto(rs.getString("txto"));
		document.setIteo(rs.getString("iteo"));
		document.setLite(rs.getString("lite"));
		document.setInte(rs.getString("inte"));

		document.setObs(rs.getString("obs"));
		document.setObpr(rs.getString("obpr"));

		document.setInde(rs.getString("inde"));
		document.setPrec(rs.getString("prec"));
		document.setSuce(rs.getString("suce"));
		document.setDout(rs.getString("dout"));
		document.setCata(rs.getString("cata"));
		document.setRefl(rs.getString("refl"));
		document.setOure(rs.getString("oure"));

		document.setFont(rs.getString("font"));
		document.setFont1(rs.getString("fnt1"));
		document.setFont2(rs.getString("fnt2"));
		document.setFont3(rs.getString("fnt3"));
		document.setOutf(rs.getString("outf"));

		document.setHisa(rs.getString("hisa"));
		return document;
	}

	/**
	 * Metodo responsavel por obter a conexao com o BRS.
	 * 
	 * @return Objeto da classe BRSConnection
	 * @exception BRSException
	 */
	private br.com.pix.brs.BRSConnection getConnection(String connection) throws br.com.pix.brs.BRSException {
		br.com.pix.brs.BRSConnection con = null;
		try {
			// con =new br.com.pix.brs.BRSConnection(config.getBRSServer(connection),
			// config.getBRSUser(connection), config.getBRSPassword(connection));

			con = pool.getConnection(config.getBRSServer(connection), config.getBRSUser(connection),
					config.getBRSPassword(connection));
			con.setDefaultOperator("e");
			logger.debug("Conexao BRS");
			return con;
		} catch (Exception e) {
			if (con != null && !con.isClosed())
				con.close();
			throw e;
		}
	}

	private BRSDate getBRSDate(String data) throws BRSException {
		// return BRSDate.getBRSDate(data, config.getBRSHighlightOn(),
		// config.getBRSHighlightOff());
		return new BRSNormalDate(data, config.getBRSHighlightOn());
	}
}