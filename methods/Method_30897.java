@Override protected void onCallRawLoadFinished(boolean more,int count,boolean successful,UserList response,ApiError error){
  onRawLoadFinished(more,count,successful,response != null ? response.users : null,error);
}
