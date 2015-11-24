package dk.jrpe.solr.test;

import java.io.IOException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SearchHandler {
	public static void main(String[] args) {
		SolrClient solr = null;
		try {
			solr = SolrClientFactory.getInstance().getClient("http://localhost:8983/solr/DP");
			SolrQuery query = new SolrQuery();
		    query.setQuery( "foundation" );
		    
		    QueryResponse rsp = solr.query(query);
		    SolrDocumentList docs = rsp.getResults();
		    for (SolrDocument solrDocument : docs) {
		    	System.out.println(solrDocument.toString());
			}
		} catch (SolrServerException | IOException e) {
			e.printStackTrace();
		} finally {
			SolrClientFactory.getInstance().closeAll();
		}
	}
}
