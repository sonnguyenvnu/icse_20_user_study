protected void flushPolys(){
  boolean customShader=polyShader != null;
  boolean needNormals=customShader ? polyShader.accessNormals() : false;
  boolean needTexCoords=customShader ? polyShader.accessTexCoords() : false;
  updatePolyBuffers(lights,texCache.hasTextures,needNormals,needTexCoords);
  for (int i=0; i < texCache.size; i++) {
    Texture tex=texCache.getTexture(i);
    PShader shader=getPolyShader(lights,tex != null);
    shader.bind();
    int first=texCache.firstCache[i];
    int last=texCache.lastCache[i];
    IndexCache cache=tessGeo.polyIndexCache;
    for (int n=first; n <= last; n++) {
      int ioffset=n == first ? texCache.firstIndex[i] : cache.indexOffset[n];
      int icount=n == last ? texCache.lastIndex[i] - ioffset + 1 : cache.indexOffset[n] + cache.indexCount[n] - ioffset;
      int voffset=cache.vertexOffset[n];
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
      for (      VertexAttribute attrib : polyAttribs.values()) {
        if (!attrib.active(shader))         continue;
        attrib.bind(pgl);
        shader.setAttributeVBO(attrib.glLoc,attrib.buf.glId,attrib.tessSize,attrib.type,attrib.isColor(),0,attrib.sizeInBytes(voffset));
      }
      shader.draw(bufPolyIndex.glId,icount,ioffset);
    }
    for (    VertexAttribute attrib : polyAttribs.values()) {
      if (attrib.active(shader))       attrib.unbind(pgl);
    }
    shader.unbind();
  }
  unbindPolyBuffers();
}
