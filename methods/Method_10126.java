/** 
 * Gets invite user count of a user specified by the given user id.
 * @param userId the given user id
 * @return invited user count
 */
public int getInvitedUserCount(final String userId){
  final Query query=new Query().setFilter(CompositeFilterOperator.and(new PropertyFilter(Pointtransfer.TO_ID,FilterOperator.EQUAL,userId),CompositeFilterOperator.or(new PropertyFilter(Pointtransfer.TYPE,FilterOperator.EQUAL,Pointtransfer.TRANSFER_TYPE_C_INVITECODE_USED),new PropertyFilter(Pointtransfer.TYPE,FilterOperator.EQUAL,Pointtransfer.TRANSFER_TYPE_C_INVITE_REGISTER))));
  try {
    return (int)pointtransferRepository.count(query);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Gets invited user count failed",e);
    return 0;
  }
}
