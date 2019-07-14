private void addStyle(Node e,StyleRun.Type type){
  if (e != null) {
    addStyle(e,e.start(),e.end() - e.start(),type);
  }
}
