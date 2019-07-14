public void notifyUpdate(boolean layoutUpdated){
  if (layoutUpdated) {
    setData(getGroups());
  }
 else {
    notifyDataSetChanged();
  }
}
