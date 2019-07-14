/** 
 * Removes votes by the specified data id.
 * @param dataId the specified data id
 * @throws RepositoryException repository exception
 */
public void removeByDataId(final String dataId) throws RepositoryException {
  remove(new Query().setFilter(new PropertyFilter(Vote.DATA_ID,FilterOperator.EQUAL,dataId)).setPageCount(1));
}
