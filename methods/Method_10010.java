/** 
 * Fils new tags.
 * @param dataModel the specified data model
 */
private void fillNewTags(final Map<String,Object> dataModel){
  dataModel.put(Common.NEW_TAGS,tagQueryService.getNewTags());
}
