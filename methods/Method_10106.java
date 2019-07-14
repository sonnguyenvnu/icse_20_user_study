/** 
 * Adds a tag-tag relation.
 * @param tagRelation the specified tag-tag relation
 * @throws ServiceException service exception
 */
void addTagRelation(final JSONObject tagRelation) throws ServiceException {
  final Transaction transaction=tagTagRepository.beginTransaction();
  try {
    tagTagRepository.add(tagRelation);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Adds a tag-tag failed",e);
    throw new ServiceException(e);
  }
}
