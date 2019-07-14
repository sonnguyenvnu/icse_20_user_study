private void notifyInsertion(int positionStart,int itemCount){
  if (!notificationsPaused && observer != null) {
    observer.onItemRangeInserted(positionStart,itemCount);
  }
}
