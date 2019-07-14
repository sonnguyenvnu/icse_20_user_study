/** 
 * Determines whether exists a follow relationship for the specified follower and the specified following entity.
 * @param followerId    the specified follower id
 * @param followingId   the specified following entity id
 * @param followingType the specified following type
 * @return {@code true} if exists, returns {@code false} otherwise
 */
public boolean isFollowing(final String followerId,final String followingId,final int followingType){
  Stopwatchs.start("Is following");
  try {
    return followRepository.exists(followerId,followingId,followingType);
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,"Determines following failed [followerId=" + followerId + ", followingId=" + followingId + "]",e);
    return false;
  }
 finally {
    Stopwatchs.end();
  }
}
