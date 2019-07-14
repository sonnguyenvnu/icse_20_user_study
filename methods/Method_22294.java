/** 
 * creates a comment label view with resCommentPrompt as text
 * @return the label or null if there is no resource
 */
@Nullable protected View getCommentLabel(){
  final String commentPrompt=dialogConfiguration.commentPrompt();
  if (commentPrompt != null) {
    final TextView labelView=new TextView(this);
    labelView.setText(commentPrompt);
    return labelView;
  }
  return null;
}
