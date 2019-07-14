@Override public void onCommentClick(CommentItem item,int position,int index,boolean isLong){
  if (isLong) {
    if (item == null || momentItem == null) {
      return;
    }
    if (APIJSONApplication.getInstance().isCurrentUser(momentItem.getUserId()) == false && APIJSONApplication.getInstance().isCurrentUser(item.getUserId()) == false) {
      showShortToast("?????????????????");
      return;
    }
    toCommentItem=item;
    new AlertDialog(context,null,"???????",true,DIALOG_DELETE_COMMENT,MomentActivity.this).show();
  }
 else {
    showInput(item,position,index);
  }
}
