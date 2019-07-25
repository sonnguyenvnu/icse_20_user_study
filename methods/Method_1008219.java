/** 
 * Sets an artificial document from which term vectors are requested for.
 */
public TermVectorsRequest doc(XContentBuilder documentBuilder){
  return this.doc(documentBuilder.bytes(),true,documentBuilder.contentType());
}
