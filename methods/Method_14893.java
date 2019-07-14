@Override public void getListAsync(final int page){
  HttpRequest.getUserList(range,id,search,idList,getCacheCount(),page,-page,this);
}
