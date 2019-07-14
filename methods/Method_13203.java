@Override public int getFileCount(API api,Container.Entry entry){
  if (entry.getPath().indexOf('$') == -1) {
    return 1;
  }
 else {
    return 0;
  }
}
