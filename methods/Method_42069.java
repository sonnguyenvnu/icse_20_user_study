@Override public Album handle(Cursor cur){
  return new Album(cur);
}
