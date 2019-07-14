protected boolean pointBuffersContextIsOutdated(){
  return !pgl.contextIsCurrent(pointBuffersContext);
}
