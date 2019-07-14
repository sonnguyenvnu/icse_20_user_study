/** 
 * The specified user vote up the specified article/comment.
 * @param userId   the specified user id
 * @param dataId   the specified article/comment id
 * @param dataType the specified data type
 */
@Transactional public void voteUp(final String userId,final String dataId,final int dataType){
  try {
    up(userId,dataId,dataType);
  }
 catch (  final RepositoryException e) {
    final String msg="User[id=" + userId + "] vote up an [" + dataType + "][id=" + dataId + "] failed";
    LOGGER.log(Level.ERROR,msg,e);
  }
  livenessMgmtService.incLiveness(userId,Liveness.LIVENESS_VOTE);
}
