public void refresh(){
synchronized (this) {
    Iterator<WeakReference<LiveResults<?>>> iterator=liveDatas.iterator();
    while (iterator.hasNext()) {
      WeakReference<LiveResults<?>> weakReference=iterator.next();
      LiveResults<?> liveData=weakReference.get();
      if (liveData == null) {
        iterator.remove();
      }
 else {
        liveData.refresh();
      }
    }
  }
}
