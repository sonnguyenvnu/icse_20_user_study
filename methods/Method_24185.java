@Override public void mask(PImage alpha){
  updatePixelSize();
  if (alpha.pixelWidth != pixelWidth || alpha.pixelHeight != pixelHeight) {
    throw new RuntimeException("The PImage used with mask() must be " + "the same size as the applet.");
  }
  PGraphicsOpenGL ppg=getPrimaryPG();
  if (ppg.maskShader == null) {
    ppg.maskShader=new PShader(parent,defTextureShaderVertURL,maskShaderFragURL);
  }
  ppg.maskShader.set("mask",alpha);
  filter(ppg.maskShader);
}
