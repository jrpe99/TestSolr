package dk.jrpe.solr.test;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.solr.client.solrj.SolrServerException;

public class TestSolr {
	public static void main(String[] args) {
		try {
			SolrDAO<Document> solrDAO = new SolrDAO<Document>("http://localhost:8983/solr/DP");
			Document document = new Document("1", "Person", "ANDET", "Jörgen Persson");
			solrDAO.save(document);

			Set<Document> documents = new HashSet<>();
			documents.add(new Document("2", "Person", "ANDET", "Carl Gustaf Liebe"));
			solrDAO.save(documents);

			solrDAO.search("Jörgen");
			Thread.sleep(2000);
			solrDAO.search("Carl");
			
		} catch (IOException | SolrServerException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
