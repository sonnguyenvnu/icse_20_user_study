@Override public void bindView(CommentItem data){
  this.data=data;
  String name=StringUtil.getTrimedString(data.getUser().getName());
  String content=StringUtil.getTrimedString(data.getComment().getContent());
  tvCommentName.setText("" + name);
  tvCommentContent.setText("" + content);
  tvCommentTime.setText("" + TimeUtil.getSmartDate(data.getDate()));
  ImageLoaderUtil.loadImage(ivCommentHead,data.getUser().getHead(),ImageLoaderUtil.TYPE_OVAL);
  setChildComment();
}
