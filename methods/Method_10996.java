public void updateData(List<T> data){
  this.setData(data);
  this.notifyDataSetChanged();
}
