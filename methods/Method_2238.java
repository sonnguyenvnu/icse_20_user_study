public void fadeInImage(int durationMs){
  setTransitionDuration(durationMs);
  beginBatchMode();
  fadeOutLayer(PLACEHOLDER_DRAWABLE_INDEX);
  fadeOutLayer(PROGRESS_DRAWABLE_INDEX);
  fadeInLayer(IMAGE_DRAWABLE_INDEX);
  endBatchMode();
}
