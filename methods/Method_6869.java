public void cancelTyping(int action,long dialog_id){
  LongSparseArray<Boolean> typings=sendingTypings.get(action);
  if (typings != null) {
    typings.remove(dialog_id);
  }
}
