/** 
 * Remove visits by the specified user id.
 * @param userId the specified user id
 * @throws RepositoryException repository exception
 */
public void removeByUserId(final String userId) throws RepositoryException {
  remove(new Query().setFilter(new PropertyFilter(Visit.VISIT_USER_ID,FilterOperator.EQUAL,userId)));
}
