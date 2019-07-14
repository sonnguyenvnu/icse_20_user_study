/** 
 * Removes a user specified by the given user id.
 * @param userId the given user id
 * @throws ServiceException service exception
 */
public void removeUser(final String userId) throws ServiceException {
  final Transaction transaction=userRepository.beginTransaction();
  try {
    userRepository.remove(userId);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Removes a user[id=" + userId + "] failed",e);
    throw new ServiceException(e);
  }
}
