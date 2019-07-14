public Item getMedium(){
  return medium != null ? medium : large != null ? large : raw != null ? raw : small;
}
