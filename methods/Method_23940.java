protected boolean contextIsOutdated(){
  if (screenFb)   return false;
  boolean outdated=!pgl.contextIsCurrent(context);
  if (outdated) {
    dispose();
    for (int i=0; i < numColorBuffers; i++) {
      colorBufferTex[i]=null;
    }
  }
  return outdated;
}
