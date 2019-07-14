protected void setParams(float[] source){
  if (params == null) {
    params=new float[source.length];
  }
  if (source.length != params.length) {
    PGraphics.showWarning("Wrong number of parameters");
    return;
  }
  PApplet.arrayCopy(source,params);
}
