private void notifyRemoval(int positionStart,int itemCount){
  if (!notificationsPaused && observer != null) {
    observer.onItemRangeRemoved(positionStart,itemCount);
  }
}
