/** 
 * ????UI??
 * @param momentItem_
 */
private void setHead(MomentItem momentItem_){
  this.momentItem=momentItem_;
  if (momentItem == null) {
    momentItem=new MomentItem(momentId);
  }
  runUiThread(new Runnable(){
    @Override public void run(){
      momentView.bindView(momentItem);
      if (showKeyboard) {
        showKeyboard=false;
        Comment comment=new Comment(toCommentId);
        User user=new User();
        user.setName(toUserName);
        showInput(new CommentItem().setComment(comment).setUser(user));
      }
    }
  }
);
}
