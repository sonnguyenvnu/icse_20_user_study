protected void updatePointBuffers(){
  createPointBuffers();
  int size=tessGeo.pointVertexCount;
  int sizef=size * PGL.SIZEOF_FLOAT;
  int sizei=size * PGL.SIZEOF_INT;
  tessGeo.updatePointVerticesBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPointVertex.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,4 * sizef,tessGeo.pointVerticesBuffer,PGL.STATIC_DRAW);
  tessGeo.updatePointColorsBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPointColor.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,sizei,tessGeo.pointColorsBuffer,PGL.STATIC_DRAW);
  tessGeo.updatePointOffsetsBuffer();
  pgl.bindBuffer(PGL.ARRAY_BUFFER,bufPointAttrib.glId);
  pgl.bufferData(PGL.ARRAY_BUFFER,2 * sizef,tessGeo.pointOffsetsBuffer,PGL.STATIC_DRAW);
  tessGeo.updatePointIndicesBuffer();
  pgl.bindBuffer(PGL.ELEMENT_ARRAY_BUFFER,bufPointIndex.glId);
  pgl.bufferData(PGL.ELEMENT_ARRAY_BUFFER,tessGeo.pointIndexCount * PGL.SIZEOF_INDEX,tessGeo.pointIndicesBuffer,PGL.STATIC_DRAW);
}
