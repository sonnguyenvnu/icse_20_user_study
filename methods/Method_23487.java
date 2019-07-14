protected void setPath(int vcount,float[][] verts,int ccount,int[] codes){
  if (verts == null || verts.length < vcount)   return;
  if (0 < ccount && (codes == null || codes.length < ccount))   return;
  int ndim=verts[0].length;
  vertexCount=vcount;
  vertices=new float[vertexCount][ndim];
  for (int i=0; i < vertexCount; i++) {
    PApplet.arrayCopy(verts[i],vertices[i]);
  }
  vertexCodeCount=ccount;
  if (0 < vertexCodeCount) {
    vertexCodes=new int[vertexCodeCount];
    PApplet.arrayCopy(codes,vertexCodes,vertexCodeCount);
  }
}
