public void removeUncalled(FunType f){
  uncalled.remove(f);
  if (f.getFunc() != null) {
    called.add(f.getFunc());
  }
}
