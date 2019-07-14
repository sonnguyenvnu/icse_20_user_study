@Override public String getLargeUrl(){
  return large != null ? large : medium != null ? medium : small;
}
