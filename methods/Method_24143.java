public void applyProjection(PMatrix3D mat){
  flush();
  projection.apply(mat);
  updateProjmodelview();
}
