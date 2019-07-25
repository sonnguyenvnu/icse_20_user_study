public void pause(){
  for (  ObjectInfo info : mObjectInfos.values()) {
    info.setResumed(false);
  }
}
