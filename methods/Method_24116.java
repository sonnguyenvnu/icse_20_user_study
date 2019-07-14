protected void flushSortedPolys(){
  boolean customShader=polyShader != null;
  boolean needNormals=customShader ? polyShader.accessNormals() : false;
  boolean needTexCoords=customShader ? polyShader.accessTexCoords() : false;
  sorter.sort(tessGeo);
  int triangleCount=tessGeo.polyIndexCount / 3;
  int[] texMap=sorter.texMap;
  int[] voffsetMap=sorter.voffsetMap;
  int[] vertexOffset=tessGeo.polyIndexCache.vertexOffset;
  updatePolyBuffers(lights,texCache.hasTextures,needNormals,needTexCoords);
  int ti=0;
  while (ti < triangleCount) {
    int startTi=ti;
    int texId=texMap[ti];
    int voffsetId=voffsetMap[ti];
    do {
      ++ti;
    }
 while (ti < triangleCount && texId == texMap[ti] && voffsetId == voffsetMap[ti]);
    int endTi=ti;
    Texture tex=texCache.getTexture(texId);
    int voffset=vertexOffset[voffsetId];
    int ioffset=3 * startTi;
    int icount=3 * (endTi - startTi);
    PShader shader=getPolyShader(lights,tex != null);
    shader.bind();
    shader.setVertexAttribute(bufPolyVertex.glId,4,PGL.FLOAT,0,4 * voffset * PGL.SIZEOF_FLOAT);
    shader.setColorAttribute(bufPolyColor.glId,4,PGL.UNSIGNED_BYTE,0,4 * voffset * PGL.SIZEOF_BYTE);
    if (lights) {
      shader.setNormalAttribute(bufPolyNormal.glId,3,PGL.FLOAT,0,3 * voffset * PGL.SIZEOF_FLOAT);
      shader.setAmbientAttribute(bufPolyAmbient.glId,4,PGL.UNSIGNED_BYTE,0,4 * voffset * PGL.SIZEOF_BYTE);
      shader.setSpecularAttribute(bufPolySpecular.glId,4,PGL.UNSIGNED_BYTE,0,4 * voffset * PGL.SIZEOF_BYTE);
      shader.setEmissiveAttribute(bufPolyEmissive.glId,4,PGL.UNSIGNED_BYTE,0,4 * voffset * PGL.SIZEOF_BYTE);
      shader.setShininessAttribute(bufPolyShininess.glId,1,PGL.FLOAT,0,voffset * PGL.SIZEOF_FLOAT);
    }
    if (lights || needNormals) {
      shader.setNormalAttribute(bufPolyNormal.glId,3,PGL.FLOAT,0,3 * voffset * PGL.SIZEOF_FLOAT);
    }
    if (tex != null || needTexCoords) {
      shader.setTexcoordAttribute(bufPolyTexcoord.glId,2,PGL.FLOAT,0,2 * voffset * PGL.SIZEOF_FLOAT);
      shader.setTexture(tex);
    }
    for (    VertexAttribute attrib : polyAttribs.values()) {
      if (!attrib.active(shader))       continue;
      attrib.bind(pgl);
      shader.setAttributeVBO(attrib.glLoc,attrib.buf.glId,attrib.tessSize,attrib.type,attrib.isColor(),0,attrib.sizeInBytes(voffset));
    }
    shader.draw(bufPolyIndex.glId,icount,ioffset);
    for (    VertexAttribute attrib : polyAttribs.values()) {
      if (attrib.active(shader))       attrib.unbind(pgl);
    }
    shader.unbind();
  }
  unbindPolyBuffers();
}
