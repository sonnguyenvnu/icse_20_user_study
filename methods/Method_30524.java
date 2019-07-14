@Override public String getSmallUrl(){
  return small != null ? small : medium != null ? medium : large;
}
