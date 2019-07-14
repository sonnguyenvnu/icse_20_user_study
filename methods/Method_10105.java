/** 
 * Updates the specified tag by the given tag id. <p> <b>Note</b>: This method just for admin console. </p>
 * @param tagId the given tag id
 * @param tag   the specified tag
 * @throws ServiceException service exception
 */
public void updateTag(final String tagId,final JSONObject tag) throws ServiceException {
  final Transaction transaction=tagRepository.beginTransaction();
  try {
    tag.put(Tag.TAG_RANDOM_DOUBLE,Math.random());
    tagRepository.update(tagId,tag);
    transaction.commit();
    tagCache.loadTags();
    domainCache.loadDomains();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates a tag[id=" + tagId + "] failed",e);
    throw new ServiceException(e);
  }
}
