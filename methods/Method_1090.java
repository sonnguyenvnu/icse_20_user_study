@Override public synchronized void onFinalImageSet(String id,@Nullable INFO imageInfo,@Nullable Animatable animatable){
  final int numberOfListeners=mListeners.size();
  for (int i=0; i < numberOfListeners; ++i) {
    try {
      ControllerListener<? super INFO> listener=mListeners.get(i);
      if (listener != null) {
        listener.onFinalImageSet(id,imageInfo,animatable);
      }
    }
 catch (    Exception exception) {
      onException("InternalListener exception in onFinalImageSet",exception);
    }
  }
}
