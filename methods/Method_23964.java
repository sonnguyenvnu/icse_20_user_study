protected int getDrawFramebuffer(){
  if (fboLayerEnabled)   return 1 < numSamples ? glMultiFbo.get(0) : glColorFbo.get(0);
 else   return 0;
}
