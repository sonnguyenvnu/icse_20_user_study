/** 
 * Determines whether exists a follow relationship for the specified follower and the specified following entity.
 * @param followerId    the specified follower id
 * @param followingId   the specified following entity id
 * @param followingType the specified following type
 * @return {@code true} if exists, returns {@code false} otherwise
 * @throws RepositoryException repository exception
 */
public boolean exists(final String followerId,final String followingId,final int followingType) throws RepositoryException {
  return null != getByFollowerIdAndFollowingId(followerId,followingId,followingType);
}
