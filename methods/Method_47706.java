@Override public void onItemRemoved(int position){
  notifyItemRemoved(position);
  observable.notifyListeners();
}
