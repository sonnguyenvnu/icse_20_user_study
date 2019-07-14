/** 
 * Check if node's comment contains author tag.
 * @param decl node
 * @param data ruleContext
 */
public void checkAuthorComment(AbstractJavaNode decl,Object data){
  Comment comment=decl.comment();
  if (null == comment) {
    ViolationUtils.addViolationWithPrecisePosition(this,decl,data,I18nResources.getMessage(MESSAGE_KEY_PREFIX + ".comment",decl.getImage()));
  }
 else {
    String commentContent=comment.getImage();
    boolean hasAuthor=AUTHOR_PATTERN.matcher(commentContent).matches();
    if (!hasAuthor) {
      ViolationUtils.addViolationWithPrecisePosition(this,decl,data,I18nResources.getMessage(MESSAGE_KEY_PREFIX + ".author",decl.getImage()));
    }
  }
}
