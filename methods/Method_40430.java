private void addStyle(int beg,int len,StyleRun.Type type){
  styles.add(new StyleRun(type,beg,len));
  offsets.add(beg);
}
