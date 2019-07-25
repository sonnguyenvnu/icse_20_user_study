public void resume(){
  for (  ObjectInfo info : mObjectInfos.values()) {
    info.setResumed(true);
  }
}
