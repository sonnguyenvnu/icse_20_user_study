@Override protected void onLoadFinished(boolean more,int count,boolean successful,CommentList response,ApiError error){
  onLoadFinished(more,count,successful,response != null ? response.comments : null,error);
}
