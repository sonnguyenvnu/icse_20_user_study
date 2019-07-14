/** 
 * The specified user vote down the specified article?comment.
 * @param userId the specified user id
 * @param dataId the specified article id
 */
@Transactional public void voteDown(final String userId,final String dataId,final int dataType){
  try {
    down(userId,dataId,dataType);
  }
 catch (  final RepositoryException e) {
    final String msg="User[id=" + userId + "] vote down an [" + dataType + "][id=" + dataId + "] failed";
    LOGGER.log(Level.ERROR,msg,e);
  }
  livenessMgmtService.incLiveness(userId,Liveness.LIVENESS_VOTE);
}
