/** 
 * Removes a comment specified with the given comment id. Calls this method will remove all existed data related with the specified comment forcibly.
 * @param commentId the given comment id
 */
@Transactional public void removeCommentByAdmin(final String commentId){
  try {
    commentRepository.removeComment(commentId);
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Removes a comment error [id=" + commentId + "]",e);
  }
}
