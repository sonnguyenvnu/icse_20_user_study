/** 
 * Relates the specified tag string.
 * @param tagString the specified tag string
 * @throws ServiceException service exception
 */
public void relateTags(final String tagString) throws ServiceException {
  final List<JSONObject> tags=new ArrayList<>();
  try {
    final String[] tagTitles=tagString.split(",");
    for (    final String tagTitle : tagTitles) {
      final JSONObject tag=tagRepository.getByTitle(tagTitle.trim());
      if (null != tag) {
        tags.add(tag);
      }
    }
    for (int i=0; i < tags.size(); i++) {
      final JSONObject tag1=tags.get(i);
      final String tag1Id=tag1.optString(Keys.OBJECT_ID);
      for (int j=i + 1; j < tags.size(); j++) {
        final JSONObject tag2=tags.get(j);
        final String tag2Id=tag2.optString(Keys.OBJECT_ID);
        JSONObject relation=tagTagRepository.getByTag1IdAndTag2Id(tag1Id,tag2Id);
        if (null != relation) {
          relation.put(Common.WEIGHT,relation.optInt(Common.WEIGHT) + 1);
          updateTagRelation(relation.optString(Keys.OBJECT_ID),relation);
          continue;
        }
        relation=tagTagRepository.getByTag1IdAndTag2Id(tag2Id,tag1Id);
        if (null != relation) {
          relation.put(Common.WEIGHT,relation.optInt(Common.WEIGHT) + 1);
          updateTagRelation(relation.optString(Keys.OBJECT_ID),relation);
          continue;
        }
        relation=new JSONObject();
        relation.put(Tag.TAG + "1_" + Keys.OBJECT_ID,tag1Id);
        relation.put(Tag.TAG + "2_" + Keys.OBJECT_ID,tag2Id);
        relation.put(Common.WEIGHT,1);
        addTagRelation(relation);
      }
    }
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Relates tag and tag [" + tagString + "] failed",e);
    throw new ServiceException(e);
  }
}
