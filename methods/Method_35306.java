private void dispatchDragCallback(float elasticOffset,float elasticOffsetPixels,float rawOffset,float rawOffsetPixels){
  if (callbacks != null && !callbacks.isEmpty()) {
    for (    ElasticDragDismissCallback callback : callbacks) {
      callback.onDrag(elasticOffset,elasticOffsetPixels,rawOffset,rawOffsetPixels);
    }
  }
}
