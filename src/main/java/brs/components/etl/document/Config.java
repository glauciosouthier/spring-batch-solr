package brs.components.etl.document;

import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class Config extends Properties {
	private static final String DIRETORIO_CONF = "/data/apps/jurisprudencia";
	private static final String FILE_NAME = "jurisprudencia.properties";
	private static final long serialVersionUID = 6127156546061614084L;
	private static Config config;
	

	private static boolean existeArquivo(String file) {
		try {
			return new File(file).exists();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	private Config() {
		try {
			if (existeArquivo(DIRETORIO_CONF + File.separator + FILE_NAME)) {
				load(new FileInputStream(DIRETORIO_CONF + File.separator + FILE_NAME));
			} else {
				load(this.getClass().getResourceAsStream("/" + FILE_NAME));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static Config instance() {
		if (config == null)
			config = new Config();
		return config;
	}

	/**
	 * Obtem a URL de acesso ao Servidor BRS
	 * 
	 * @return A URL no formato <b>brs://nomedoservidor:6002</b>
	 */

	public String getBRSServer(String connection) {
		return this.getProperty(connection + ".brs.server", "");
	}

	/**
	 * Obtem o usuario de acesso ao Servidor BRS
	 */

	public String getBRSUser(String connection) {
		return this.getProperty(connection + ".brs.user", "");
	}

	/**
	 * Obtem a a senha de acesso ao Servidor BRS
	 */

	public String getBRSPassword(String connection) {
		return this.getProperty(connection + ".brs.password", "");
	}

	/**
	 * Obtem a base BRS
	 */

	public String getBRSDB(String connection) {
		return this.getProperty(connection + ".brs.db", "");
	}

	/**
	 * Obtem a tag de formatacao inicial para Highligh
	 */

	public String getBRSHighlightOn() {
		return this.getProperty("brs.highlight.on", "");
	}

	/**
	 * Obtem a tag de formatacao final para Highligh
	 */
	public String getBRSHighlightOff() {
		return this.getProperty("brs.highlight.off", "");
	}

	/**
	 * Obtem a definicao de plural.
	 */

	public String getBRSPlural() {
		return this.getProperty("brs.plural", "true");
	}

	/**
	 * Obtem o endereco para o documentos do interio teor
	 */
	public String getLinkIteiroTeor() {
		return this.getProperty("link.teor", "");
	}

	public String getRecaptchaSecret() {
		return this.getProperty("recaptchaSecret", "");
	}

	public String getRecaptchaPublic() {
		return this.getProperty("recaptchaPublic", "");
	}

	public String getRecaptchaVerifyUrl() {
		return this.getProperty("recaptchaVerifyUrl", "https://www.google.com/recaptcha/api/siteverify");
	}

	public String[] getRecaptchaWhiteList() {
		return this.getProperty("recaptchaWhiteList", "127.0.0.1;localhost").split(";");
	}

	public Boolean isRecaptchaEnabled() {
		return "true".equalsIgnoreCase(this.getProperty("recaptchaEnabled", "false"));
	}

	public List<String> getSolrUrls() {
		return Arrays.asList(this.getProperty("solr.host.list", "http://localhost:8983/solr").split(";"));
	}

	public boolean isJobsAtivos() {
		return "true".equalsIgnoreCase(this.getProperty("jobs.ativos", "false"));
	}
}