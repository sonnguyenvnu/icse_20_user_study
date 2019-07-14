/** 
 * User generates an invitecode.
 * @param userId   the specified user id
 * @param userName the specified user name
 * @return invitecode
 */
public String userGenInvitecode(final String userId,final String userName){
  final Transaction transaction=invitecodeRepository.beginTransaction();
  try {
    final String ret=RandomStringUtils.randomAlphanumeric(16);
    final JSONObject invitecode=new JSONObject();
    invitecode.put(Invitecode.CODE,ret);
    invitecode.put(Invitecode.MEMO,"User [" + userName + "," + userId + "] generated");
    invitecode.put(Invitecode.STATUS,Invitecode.STATUS_C_UNUSED);
    invitecode.put(Invitecode.GENERATOR_ID,userId);
    invitecode.put(Invitecode.USER_ID,"");
    invitecode.put(Invitecode.USE_TIME,0);
    invitecodeRepository.add(invitecode);
    transaction.commit();
    return ret;
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Generates invitecode failed",e);
    return null;
  }
}
