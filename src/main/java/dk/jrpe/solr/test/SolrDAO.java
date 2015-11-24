package dk.jrpe.solr.test;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;

public class SolrDAO<T> {
	private SolrClient client = null;

	public SolrDAO(String solrUrl) {
		client = SolrClientFactory.getInstance().getClient(solrUrl);
	}

	public void search(String criteria) throws SolrServerException, IOException {
		SolrQuery query = new SolrQuery();
	    query.setQuery(criteria);
	    
	    QueryResponse rsp = client.query(query);
	    SolrDocumentList docs = rsp.getResults();
	    for (SolrDocument solrDocument : docs) {
	    	System.out.println(solrDocument.toString());
		}
	}
	public void save(T bean) throws IOException, SolrServerException {
		save(createCollection(bean));
	}

	public UpdateResponse save(Collection<T> beans) throws IOException, SolrServerException {
		return client.addBeans(beans);
	}

	public UpdateResponse delete(String id) throws SolrServerException, IOException {
		return client.deleteById(id);
	}
	
	public UpdateResponse commit() throws SolrServerException, IOException {
		return client.commit();		
	}
	
	private <U> Collection<U> createCollection(U bean) {
		if(bean == null) {
			return Collections.emptySet();
		} else {
			return Collections.singleton(bean);
		}
	}
}
