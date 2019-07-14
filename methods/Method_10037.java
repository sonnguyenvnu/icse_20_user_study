/** 
 * Updates the specified invitecode by the given invitecode id.
 * @param invitecodeId the given invitecode id
 * @param invitecode   the specified invitecode
 * @throws ServiceException service exception
 */
public void updateInvitecode(final String invitecodeId,final JSONObject invitecode) throws ServiceException {
  final Transaction transaction=invitecodeRepository.beginTransaction();
  try {
    invitecodeRepository.update(invitecodeId,invitecode);
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Updates an invitecode[id=" + invitecodeId + "] failed",e);
    throw new ServiceException(e);
  }
}
