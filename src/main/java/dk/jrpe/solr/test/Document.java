package dk.jrpe.solr.test;

import org.apache.solr.client.solrj.beans.Field;

public class Document {
	@Field("id")
	String id;
	@Field("documentTitle")
	String documentTitle;
	@Field("documentType")
	String documentType;
	@Field("documentText")
	String documentText;

	public Document() {
	}
	
	public Document(String id, String documentTitle, String documentType, String documentText) {
		this.id = id;
		this.documentTitle = documentTitle;
		this.documentType = documentType;
		this.documentText = documentText;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDocumentTitle() {
		return documentTitle;
	}
	public void setDocumentTitle(String documentTitle) {
		this.documentTitle = documentTitle;
	}
	public String getDocumentType() {
		return documentType;
	}
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	public String getDocumentText() {
		return documentText;
	}
	public void setDocumentText(String documentText) {
		this.documentText = documentText;
	}
}
