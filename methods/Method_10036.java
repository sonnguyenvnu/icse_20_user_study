/** 
 * Admin generates invitecodes with the specified quantity and memo.
 * @param quantity the specified quantity
 * @param memo     the specified memo
 */
public void adminGenInvitecodes(final int quantity,final String memo){
  final Transaction transaction=invitecodeRepository.beginTransaction();
  try {
    for (int i=0; i < quantity; i++) {
      final JSONObject invitecode=new JSONObject();
      invitecode.put(Invitecode.CODE,RandomStringUtils.randomAlphanumeric(16));
      invitecode.put(Invitecode.MEMO,memo);
      invitecode.put(Invitecode.STATUS,Invitecode.STATUS_C_UNUSED);
      invitecode.put(Invitecode.GENERATOR_ID,Pointtransfer.ID_C_SYS);
      invitecode.put(Invitecode.USER_ID,"");
      invitecode.put(Invitecode.USE_TIME,0);
      invitecodeRepository.add(invitecode);
    }
    transaction.commit();
  }
 catch (  final RepositoryException e) {
    if (transaction.isActive()) {
      transaction.rollback();
    }
    LOGGER.log(Level.ERROR,"Generates invitecodes failed",e);
  }
}
