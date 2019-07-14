/** 
 * creates a comment prompt
 * @param savedComment the content of the prompt (usually from a saved state)
 * @return the comment prompt
 */
@NonNull protected EditText getCommentPrompt(@Nullable CharSequence savedComment){
  final EditText userCommentView=new EditText(this);
  userCommentView.setLines(2);
  if (savedComment != null) {
    userCommentView.setText(savedComment);
  }
  return userCommentView;
}
