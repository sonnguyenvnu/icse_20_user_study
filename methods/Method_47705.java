@Override public void onItemMoved(int fromPosition,int toPosition){
  notifyItemMoved(fromPosition,toPosition);
  observable.notifyListeners();
}
