@Override public void render(Vector vector){
  notifyResult("final float scaleX = w / " + vector.viewportWidth + "f;");
  notifyResult("final float scaleY = h / " + vector.viewportHeight + "f;");
  if (hasPathNeedMinScale(vector.children)) {
    notifyResult("final float minScale = Math.min(scaleX, scaleY);");
  }
  writeNewLine();
}
