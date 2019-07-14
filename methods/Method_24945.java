public int compare(Handle handle1,Handle handle2){
  int tab=handle1.tabIndex - handle2.tabIndex;
  if (tab != 0) {
    return tab;
  }
  return handle1.startChar - handle2.startChar;
}
