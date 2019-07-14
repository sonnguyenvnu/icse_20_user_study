protected void lightNormal(int num,float dx,float dy,float dz){
  float nx=dx * modelviewInv.m00 + dy * modelviewInv.m10 + dz * modelviewInv.m20;
  float ny=dx * modelviewInv.m01 + dy * modelviewInv.m11 + dz * modelviewInv.m21;
  float nz=dx * modelviewInv.m02 + dy * modelviewInv.m12 + dz * modelviewInv.m22;
  float d=PApplet.dist(0,0,0,nx,ny,nz);
  if (0 < d) {
    float invn=1.0f / d;
    lightNormal[3 * num + 0]=invn * nx;
    lightNormal[3 * num + 1]=invn * ny;
    lightNormal[3 * num + 2]=invn * nz;
  }
 else {
    lightNormal[3 * num + 0]=0;
    lightNormal[3 * num + 1]=0;
    lightNormal[3 * num + 2]=0;
  }
}
