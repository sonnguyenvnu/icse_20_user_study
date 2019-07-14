@Override public void onDialogButtonClick(int requestCode,boolean isPositive){
  if (isPositive) {
    deleteComment(toCommentItem);
  }
}
