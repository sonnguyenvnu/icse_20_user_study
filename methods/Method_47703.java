@Override public void onItemChanged(int position){
  notifyItemChanged(position);
  observable.notifyListeners();
}
