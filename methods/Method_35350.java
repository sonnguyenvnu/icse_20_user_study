public void addData(List<T> data){
  if (mData == null) {
    mData=data;
  }
 else {
    mData.addAll(data);
  }
}
