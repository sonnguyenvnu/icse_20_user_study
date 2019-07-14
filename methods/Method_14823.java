/** 
 * ????(??)
 */
public void sendComment(){
  String content=StringUtil.getTrimedString(etMomentInput);
  if (StringUtil.isNotEmpty(content,true) == false) {
    showShortToast("??????");
    return;
  }
  long toCommentId=toCommentItem == null ? 0 : toCommentItem.getId();
  long toUserId=toCommentId <= 0 ? 0 : toCommentItem.getUserId();
  HttpRequest.addComment(momentId,toCommentId,toUserId,content,toCommentId <= 0 ? HTTP_COMMENT : HTTP_REPLY,this);
  hideKeyboard();
}
