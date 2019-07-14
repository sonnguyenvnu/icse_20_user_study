/** 
 * ??????
 * @param toCommentItem_
 * @param position
 */
public void showInput(CommentItem toCommentItem_,final int position,final int index){
  this.toCommentItem=toCommentItem_;
  final long toCommentId=toCommentItem == null ? 0 : toCommentItem.getComment().getId();
  runUiThread(new Runnable(){
    @Override public void run(){
      if (toCommentId <= 0 || toCommentItem == null) {
        etMomentInput.setHint("??");
      }
 else {
        etMomentInput.setHint("???" + StringUtil.getTrimedString(toCommentItem.getUser().getName()));
      }
      EditTextUtil.showKeyboard(context,etMomentInput);
      if (position >= 0) {
        new Handler().postDelayed(new Runnable(){
          @Override public void run(){
            if (isAlive()) {
              lvBaseList.setSelection(position + lvBaseList.getHeaderViewsCount());
            }
          }
        }
,500);
      }
    }
  }
);
}
