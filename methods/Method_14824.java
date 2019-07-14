@Override public void getListAsync(final int page){
  if (loadHead && page <= HttpManager.PAGE_NUM_0) {
    HttpRequest.getMoment(momentId,HTTP_GET_MOMENT,MomentActivity.this);
  }
  HttpRequest.getCommentList(momentId,0,page,-page,this);
}
