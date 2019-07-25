void fill(long hIntersected,float[] result){
  intersected=(Node)Object3D.getInstance(hIntersected);
  distance=result[0];
  submeshIndex=(int)result[1];
  textureS[0]=result[2];
  textureS[1]=result[3];
  textureT[0]=result[4];
  textureT[1]=result[5];
  normal[0]=result[6];
  normal[1]=result[7];
  normal[2]=result[8];
  ray[0]=result[9];
  ray[1]=result[10];
  ray[2]=result[11];
  ray[3]=result[12];
  ray[4]=result[13];
  ray[5]=result[14];
}
