/** 
 * Set indices and types to the search request.
 */
private void setIndicesAndTypes(){
  request.setIndices(query.getIndexArr());
  String[] typeArr=query.getTypeArr();
  if (typeArr != null) {
    request.setTypes(typeArr);
  }
}
