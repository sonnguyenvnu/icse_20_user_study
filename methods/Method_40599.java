public void addUncalled(@NotNull FunType cl){
  if (!cl.func.called) {
    uncalled.add(cl);
  }
}
