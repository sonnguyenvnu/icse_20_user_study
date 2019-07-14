public void removeData(List<T> data){
  if (data != null) {
    this.mData.removeAll(data);
    notifyDataSetChanged();
  }
}
