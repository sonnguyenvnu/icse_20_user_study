/** 
 * Removes User-Tag relations by the specified user id, type and tag ids of the relations to be removed.
 * @param userId the specified article id
 * @param type   the specified type
 * @param tagIds the specified tag ids of the relations to be removed
 * @throws RepositoryException repository exception
 */
private void removeUserTagRelations(final String userId,final int type,final String... tagIds) throws RepositoryException {
  for (  final String tagId : tagIds) {
    userTagRepository.removeByUserIdAndTagId(userId,tagId,type);
  }
}
