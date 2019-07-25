public synchronized List<SceneUpdate> get(){
  if (sceneUpdates == null) {
    sceneUpdates=create(getQuestIconResourceIds());
  }
  return sceneUpdates;
}
