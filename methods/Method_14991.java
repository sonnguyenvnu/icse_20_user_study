public boolean getIsCommented(final long userId){
  if (userId <= 0) {
    isCommented=false;
  }
 else   if (isCommented == null) {
    isCommented=false;
    List<CommentItem> commentItemList=getCommentItemList();
    if (commentItemList != null) {
      for (      CommentItem comment : commentItemList) {
        if (comment != null && comment.getComment().getUserId() == userId) {
          isCommented=true;
          break;
        }
      }
    }
  }
  return value(isCommented);
}
