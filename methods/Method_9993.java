/** 
 * Organizes the specified comment. <ul> <li>converts comment create time (long) to date type</li> <li>generates comment author thumbnail URL</li> <li>generates comment author URL</li> <li>generates comment author name</li> <li>generates &#64;username home URL</li> <li>markdowns comment content</li> <li>block comment if need</li> <li>generates emotion images</li> <li>generates time ago text</li> <li>anonymous process</li> </ul>
 * @param comment the specified comment
 * @throws RepositoryException repository exception
 */
private void organizeComment(final JSONObject comment) throws RepositoryException {
  Stopwatchs.start("Organize comment");
  try {
    comment.put(Common.TIME_AGO,Times.getTimeAgo(comment.optLong(Comment.COMMENT_CREATE_TIME),Locales.getLocale()));
    final Date createDate=new Date(comment.optLong(Comment.COMMENT_CREATE_TIME));
    comment.put(Comment.COMMENT_CREATE_TIME,createDate);
    comment.put(Comment.COMMENT_CREATE_TIME_STR,DateFormatUtils.format(createDate,"yyyy-MM-dd HH:mm:ss"));
    final String authorId=comment.optString(Comment.COMMENT_AUTHOR_ID);
    final JSONObject author=userRepository.get(authorId);
    comment.put(Comment.COMMENT_T_COMMENTER,author);
    if (Comment.COMMENT_ANONYMOUS_C_PUBLIC == comment.optInt(Comment.COMMENT_ANONYMOUS)) {
      comment.put(Comment.COMMENT_T_AUTHOR_NAME,author.optString(User.USER_NAME));
      comment.put(Comment.COMMENT_T_AUTHOR_URL,author.optString(User.USER_URL));
      final String thumbnailURL=avatarQueryService.getAvatarURLByUser(author,"48");
      comment.put(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL,thumbnailURL);
    }
 else {
      comment.put(Comment.COMMENT_T_AUTHOR_NAME,UserExt.ANONYMOUS_USER_NAME);
      comment.put(Comment.COMMENT_T_AUTHOR_URL,"");
      comment.put(Comment.COMMENT_T_AUTHOR_THUMBNAIL_URL,avatarQueryService.getDefaultAvatarURL("48"));
    }
    processCommentContent(comment);
  }
  finally {
    Stopwatchs.end();
  }
}
