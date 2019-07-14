protected void updatePolyBuffers(boolean lit,boolean tex,boolean needNormals,boolean needTexCoords){
  createPolyBuffers();
  int size=tessGeo.polyVertexCount;
  int sizef=size * PGL.SIZEOF_FLOAT;
  int sizei=size * PGL.SIZEOF_INT;
  tessGeo.updatePolyVerticesBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyVertex.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,4 * sizef,tessGeo.polyVerticesBuffer,PGL.STATIC_DRAW);
  tessGeo.updatePolyColorsBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyColor.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,sizei,tessGeo.polyColorsBuffer,PGL.STATIC_DRAW);
  if (lit) {
    tessGeo.updatePolyAmbientBuffer();
    pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyAmbient.glId);
    pgl.bufferData(PGL.ARRAY_BUFFER,sizei,tessGeo.polyAmbientBuffer,PGL.STATIC_DRAW);
    tessGeo.updatePolySpecularBuffer();
    pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolySpecular.glId);
    pgl.bufferData(PGL.ARRAY_BUFFER,sizei,tessGeo.polySpecularBuffer,PGL.STATIC_DRAW);
    tessGeo.updatePolyEmissiveBuffer();
    pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyEmissive.glId);
    pgl.bufferData(PGL.ARRAY_BUFFER,sizei,tessGeo.polyEmissiveBuffer,PGL.STATIC_DRAW);
    tessGeo.updatePolyShininessBuffer();
    pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyShininess.glId);
    pgl.bufferData(PGL.ARRAY_BUFFER,sizef,tessGeo.polyShininessBuffer,PGL.STATIC_DRAW);
  }
  if (lit || needNormals) {
    tessGeo.updatePolyNormalsBuffer();
    pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyNormal.glId);
    pgl.bufferData(PGL.ARRAY_BUFFER,3 * sizef,tessGeo.polyNormalsBuffer,PGL.STATIC_DRAW);
  }
  if (tex || needTexCoords) {
    tessGeo.updatePolyTexCoordsBuffer();
    pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPolyTexcoord.glId);
    pgl.bufferData(PGL.ARRAY_BUFFER,2 * sizef,tessGeo.polyTexCoordsBuffer,PGL.STATIC_DRAW);
  }
  for (  String name : polyAttribs.keySet()) {
    VertexAttribute attrib=polyAttribs.get(name);
    tessGeo.updateAttribBuffer(name);
    pgl.bindBuffer(PGL.ARRAY_BUFFER,attrib.buf.glId);
    pgl.bufferData(PGL.ARRAY_BUFFER,attrib.sizeInBytes(size),tessGeo.polyAttribBuffers.get(name),PGL.STATIC_DRAW);
  }
  tessGeo.updatePolyIndicesBuffer();
  pgl.bindBuffer(PGL.ELEMENT_ARRAY_BUFFER,bufPolyIndex.glId);
  pgl.bufferData(PGL.ELEMENT_ARRAY_BUFFER,tessGeo.polyIndexCount * PGL.SIZEOF_INDEX,tessGeo.polyIndicesBuffer,PGL.STATIC_DRAW);
}
