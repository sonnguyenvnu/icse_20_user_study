@Override protected void begin2D(){
  pushProjection();
  ortho(-width / 2f,width / 2f,-height / 2f,height / 2f);
  pushMatrix();
  float centerX=width / 2f;
  float centerY=height / 2f;
  modelview.reset();
  modelview.translate(-centerX,-centerY);
  modelviewInv.set(modelview);
  modelviewInv.invert();
  camera.set(modelview);
  cameraInv.set(modelviewInv);
  updateProjmodelview();
}
