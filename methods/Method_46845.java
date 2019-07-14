public void toggleChecked(boolean check){
  int k=0;
  for (int i=k; i < items.size(); i++) {
    itemsChecked[i]=check;
    notifyItemChanged(i);
  }
}
