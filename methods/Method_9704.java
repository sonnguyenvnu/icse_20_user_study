/** 
 * Loads new tags.
 */
private void loadNewTags(){
  final BeanManager beanManager=BeanManager.getInstance();
  final TagRepository tagRepository=beanManager.getReference(TagRepository.class);
  final Query query=new Query().addSort(Keys.OBJECT_ID,SortDirection.DESCENDING).setPage(1,Symphonys.SIDE_TAGS_CNT).setPageCount(1);
  query.setFilter(new PropertyFilter(Tag.TAG_REFERENCE_CNT,FilterOperator.GREATER_THAN,0));
  try {
    final JSONObject result=tagRepository.get(query);
    NEW_TAGS.clear();
    NEW_TAGS.addAll(CollectionUtils.jsonArrayToList(result.optJSONArray(Keys.RESULTS)));
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets new tags failed",e);
  }
}
