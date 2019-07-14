/** 
 * ??View
 * @param item
 */
public void setView(CommentItem item){
  if (item == null) {
    item=new CommentItem();
  }
  this.comment=item.getComment();
  this.user=item.getUser();
  this.toUser=item.getToUser();
  String content=StringUtil.getTrimedString(comment.getContent());
  String userName=StringUtil.getTrimedString(user.getName());
  int userNameLength=userName.length();
  String toUserName=StringUtil.getTrimedString(toUser.getName());
  SpannableString msp=null;
  if (toUser.getId() <= 0) {
    msp=new SpannableString(userName + " : " + content);
  }
 else {
    msp=new SpannableString(userName + " ?? " + toUserName + " : " + content);
    setSpan(msp,1,userNameLength + 4,userNameLength + 4 + toUserName.length(),toUser);
  }
  setSpan(msp,0,0,userNameLength,user);
  setText(msp);
  setMovementMethod(LinkMovementMethod.getInstance());
}
