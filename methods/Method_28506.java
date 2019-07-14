public void addItem(M item){
  removeProgress();
  data.add(item);
  if (data.size() == 0) {
    notifyDataSetChanged();
  }
 else {
    notifyItemInserted(data.size() - 1);
  }
}
