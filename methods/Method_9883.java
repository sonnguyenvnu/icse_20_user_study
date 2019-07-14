/** 
 * Removes notifications by the specified data id.
 * @param dataId the specified data id
 * @throws RepositoryException repository exception
 */
public void removeByDataId(final String dataId) throws RepositoryException {
  remove(new Query().setFilter(new PropertyFilter(Notification.NOTIFICATION_DATA_ID,FilterOperator.EQUAL,dataId)));
}
