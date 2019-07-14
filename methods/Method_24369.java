@Override public PShape getTessellation(){
  updateTessellation();
  float[] vertices=tessGeo.polyVertices;
  float[] normals=tessGeo.polyNormals;
  int[] color=tessGeo.polyColors;
  float[] uv=tessGeo.polyTexCoords;
  short[] indices=tessGeo.polyIndices;
  PShape tess;
  tess=pg.createShapeFamily(PShape.GEOMETRY);
  tess.set3D(is3D);
  tess.beginShape(TRIANGLES);
  tess.noStroke();
  IndexCache cache=tessGeo.polyIndexCache;
  for (int n=firstPolyIndexCache; n <= lastPolyIndexCache; n++) {
    int ioffset=cache.indexOffset[n];
    int icount=cache.indexCount[n];
    int voffset=cache.vertexOffset[n];
    for (int tr=ioffset / 3; tr < (ioffset + icount) / 3; tr++) {
      int i0=voffset + indices[3 * tr + 0];
      int i1=voffset + indices[3 * tr + 1];
      int i2=voffset + indices[3 * tr + 2];
      if (is3D()) {
        float x0=vertices[4 * i0 + 0];
        float y0=vertices[4 * i0 + 1];
        float z0=vertices[4 * i0 + 2];
        float x1=vertices[4 * i1 + 0];
        float y1=vertices[4 * i1 + 1];
        float z1=vertices[4 * i1 + 2];
        float x2=vertices[4 * i2 + 0];
        float y2=vertices[4 * i2 + 1];
        float z2=vertices[4 * i2 + 2];
        float nx0=normals[3 * i0 + 0];
        float ny0=normals[3 * i0 + 1];
        float nz0=normals[3 * i0 + 2];
        float nx1=normals[3 * i1 + 0];
        float ny1=normals[3 * i1 + 1];
        float nz1=normals[3 * i1 + 2];
        float nx2=normals[3 * i2 + 0];
        float ny2=normals[3 * i2 + 1];
        float nz2=normals[3 * i2 + 2];
        int argb0=PGL.nativeToJavaARGB(color[i0]);
        int argb1=PGL.nativeToJavaARGB(color[i1]);
        int argb2=PGL.nativeToJavaARGB(color[i2]);
        tess.fill(argb0);
        tess.normal(nx0,ny0,nz0);
        tess.vertex(x0,y0,z0,uv[2 * i0 + 0],uv[2 * i0 + 1]);
        tess.fill(argb1);
        tess.normal(nx1,ny1,nz1);
        tess.vertex(x1,y1,z1,uv[2 * i1 + 0],uv[2 * i1 + 1]);
        tess.fill(argb2);
        tess.normal(nx2,ny2,nz2);
        tess.vertex(x2,y2,z2,uv[2 * i2 + 0],uv[2 * i2 + 1]);
      }
 else       if (is2D()) {
        float x0=vertices[4 * i0 + 0], y0=vertices[4 * i0 + 1];
        float x1=vertices[4 * i1 + 0], y1=vertices[4 * i1 + 1];
        float x2=vertices[4 * i2 + 0], y2=vertices[4 * i2 + 1];
        int argb0=PGL.nativeToJavaARGB(color[i0]);
        int argb1=PGL.nativeToJavaARGB(color[i1]);
        int argb2=PGL.nativeToJavaARGB(color[i2]);
        tess.fill(argb0);
        tess.vertex(x0,y0,uv[2 * i0 + 0],uv[2 * i0 + 1]);
        tess.fill(argb1);
        tess.vertex(x1,y1,uv[2 * i1 + 0],uv[2 * i1 + 1]);
        tess.fill(argb2);
        tess.vertex(x2,y2,uv[2 * i2 + 0],uv[2 * i2 + 1]);
      }
    }
  }
  tess.endShape();
  return tess;
}
