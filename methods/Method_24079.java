protected boolean lineBufferContextIsOutdated(){
  return !pgl.contextIsCurrent(lineBuffersContext);
}
