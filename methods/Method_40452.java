private void addStyle(Node e,int start,int len,StyleRun.Type type){
  if (e == null || e.getFile() == null) {
    return;
  }
  addStyle(start,len,type);
}
