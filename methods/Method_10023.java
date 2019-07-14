/** 
 * Gets a user's emotion (emoji with type=0).
 * @param userId the specified user id
 * @return emoji string join with {@code ","}, returns a common used emoji string if not found
 */
public String getEmojis(final String userId){
  try {
    final String ret=emotionRepository.getUserEmojis(userId);
    if (StringUtils.isBlank(ret)) {
      return COMMON_USED;
    }
    return ret;
  }
 catch (  final RepositoryException e) {
    LOGGER.log(Level.ERROR,e.getMessage());
    return COMMON_USED;
  }
}
