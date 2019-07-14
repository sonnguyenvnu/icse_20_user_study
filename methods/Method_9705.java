/** 
 * Loads icon tags.
 */
private void loadIconTags(){
  final BeanManager beanManager=BeanManager.getInstance();
  final TagRepository tagRepository=beanManager.getReference(TagRepository.class);
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Tag.TAG_ICON_PATH,FilterOperator.NOT_EQUAL,""),new PropertyFilter(Tag.TAG_STATUS,FilterOperator.EQUAL,Tag.TAG_STATUS_C_VALID))).setPage(1,Integer.MAX_VALUE).setPageCount(1).addSort(Tag.TAG_RANDOM_DOUBLE,SortDirection.ASCENDING);
  try {
    final JSONObject result=tagRepository.get(query);
    final List<JSONObject> tags=CollectionUtils.jsonArrayToList(result.optJSONArray(Keys.RESULTS));
    final List<JSONObject> toUpdateTags=new ArrayList<>();
    for (    final JSONObject tag : tags) {
      toUpdateTags.add(JSONs.clone(tag));
    }
    for (    final JSONObject tag : tags) {
      Tag.fillDescription(tag);
      tag.put(Tag.TAG_T_TITLE_LOWER_CASE,tag.optString(Tag.TAG_TITLE).toLowerCase());
    }
    ICON_TAGS.clear();
    ICON_TAGS.addAll(tags);
    final Transaction transaction=tagRepository.beginTransaction();
    for (    final JSONObject tag : toUpdateTags) {
      tag.put(Tag.TAG_RANDOM_DOUBLE,Math.random());
      tagRepository.update(tag.optString(Keys.OBJECT_ID),tag);
    }
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Load icon tags failed",e);
  }
}
