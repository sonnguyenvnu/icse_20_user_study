private void updateSelectedCount(){
  if (placeProvider == null) {
    return;
  }
  int count=placeProvider.getSelectedCount();
  photosCounterView.setCount(count);
  if (count == 0) {
    togglePhotosListView(false,true);
  }
}
