@Override public void invalidateNewerThan(Timestamp timestamp){
  list.clear();
  observable.notifyListeners();
}
