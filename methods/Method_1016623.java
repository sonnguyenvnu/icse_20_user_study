/** 
 * Formatting XML string resolving URIs according its base location  
 */
public String format(String raw,String baseLocation) throws Exception {
  IStructuredDocument document=new BasicStructuredDocument(new XMLSourceParser());
  document.setPreferredLineDelimiter(LINE_DELIMITER);
  IDocumentPartitioner partitioner=new StructuredTextPartitionerForXML();
  document.setDocumentPartitioner(new StructuredTextPartitionerForXML());
  partitioner.connect(document);
  document.set(raw);
  DOMModelImpl xmlDOM=new DOMModelImpl();
  xmlDOM.setBaseLocation(baseLocation);
  xmlDOM.getFactoryRegistry().addFactory(xmlAdapterFactory);
  xmlDOM.setStructuredDocument(document);
  ModelQuery modelQuery=ModelQueryUtil.getModelQuery(xmlDOM);
  modelQuery.getCMDocumentManager().setPropertyEnabled(CMDocumentManager.PROPERTY_USE_CACHED_RESOLVED_URI,true);
  TextEdit formatterChanges=formatter.format(xmlDOM,0,document.getLength(),preferences);
  formatterChanges.apply(document);
  return document.get();
}
