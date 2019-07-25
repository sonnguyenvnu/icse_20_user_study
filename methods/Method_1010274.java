@Override public void load(@NotNull Memento memento){
  checkNotRegistered();
  path=memento.get("path");
}
