protected void tessellateSphere(){
  float r=0;
  int nu=sphereDetailU;
  int nv=sphereDetailV;
  if (1 <= params.length) {
    r=params[0];
    if (params.length == 2) {
      nu=nv=(int)params[1];
    }
 else     if (params.length == 3) {
      nu=(int)params[1];
      nv=(int)params[2];
    }
  }
  if (nu < 3 || nv < 2) {
    nu=nv=30;
  }
  int savedDetailU=pg.sphereDetailU;
  int savedDetailV=pg.sphereDetailV;
  if (pg.sphereDetailU != nu || pg.sphereDetailV != nv) {
    pg.sphereDetail(nu,nv);
  }
  inGeo.setMaterial(fillColor,strokeColor,strokeWeight,ambientColor,specularColor,emissiveColor,shininess);
  int[] indices=inGeo.addSphere(r,nu,nv,fill,stroke);
  tessellator.tessellateTriangles(indices);
  if ((0 < savedDetailU && savedDetailU != nu) || (0 < savedDetailV && savedDetailV != nv)) {
    pg.sphereDetail(savedDetailU,savedDetailV);
  }
}
