/** 
 * The specified follower follows the specified following user.
 * @param followerId      the specified follower id
 * @param followingUserId the specified following user id
 */
@Transactional public void followUser(final String followerId,final String followingUserId){
  try {
    follow(followerId,followingUserId,Follow.FOLLOWING_TYPE_C_USER);
  }
 catch (  final RepositoryException e) {
    final String msg="User[id=" + followerId + "] follows a user[id=" + followingUserId + "] failed";
    LOGGER.log(Level.ERROR,msg,e);
  }
}
