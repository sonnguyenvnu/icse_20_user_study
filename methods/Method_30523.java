@Override public String getMediumUrl(){
  return medium != null ? medium : large != null ? large : small;
}
