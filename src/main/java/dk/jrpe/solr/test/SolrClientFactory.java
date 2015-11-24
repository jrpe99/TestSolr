package dk.jrpe.solr.test;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.impl.HttpSolrClient;

public class SolrClientFactory {
	private static final String DEFAULT_SOLR_URL = "http://localhost:8983/solr/gettingstarted_shard1_replica2";
	private Map <String, SolrClient> urlToServer = new ConcurrentHashMap <String, SolrClient> ();
	
	private static class InstanceHolder {
		static final SolrClientFactory INSTANCE = new SolrClientFactory();
	}
	public static SolrClientFactory getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public SolrClient getClient() {
		return getClient(DEFAULT_SOLR_URL);
	}
	
	public SolrClient getClient(String solrUrl) {
		if(urlToServer.containsKey(solrUrl)) {
			return urlToServer.get(solrUrl);
		} else {
			SolrClient solrClient = new HttpSolrClient(solrUrl);
			urlToServer.put(solrUrl, solrClient);
			return solrClient;
		}
	}
	
	public void closeAll() {
		for (Entry<String, SolrClient> entry : urlToServer.entrySet()) {
			SolrClient client = entry.getValue();
			try {
				client.close();
			} catch (IOException e) {
				System.out.println("Could not close client : " + entry.getKey());
			}
		}
	}
}
