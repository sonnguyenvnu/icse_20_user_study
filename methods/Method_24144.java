public void applyProjection(float n00,float n01,float n02,float n03,float n10,float n11,float n12,float n13,float n20,float n21,float n22,float n23,float n30,float n31,float n32,float n33){
  flush();
  projection.apply(n00,n01,n02,n03,n10,n11,n12,n13,n20,n21,n22,n23,n30,n31,n32,n33);
  updateProjmodelview();
}
