public Item getLarge(){
  return raw != null ? raw : large != null ? large : medium != null ? medium : small;
}
