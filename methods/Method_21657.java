/** 
 * Set indices and types to the delete by query request.
 */
private void setIndicesAndTypes(){
  DeleteByQueryRequest innerRequest=request.request();
  innerRequest.indices(query.getIndexArr());
  String[] typeArr=query.getTypeArr();
  if (typeArr != null) {
    innerRequest.getSearchRequest().types(typeArr);
  }
}
