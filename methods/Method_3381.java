@Override public int idOf(String string){
  int id=super.idOf(string);
  if (id == -1 && mutable) {
    id=dat.size();
    dat.put(string,id);
  }
  return id;
}
