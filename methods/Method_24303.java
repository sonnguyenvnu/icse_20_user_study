protected void scaleTextureUV(float uFactor,float vFactor){
  if (PGraphicsOpenGL.same(uFactor,1) && PGraphicsOpenGL.same(vFactor,1))   return;
  for (int i=0; i < inGeo.vertexCount; i++) {
    float u=inGeo.texcoords[2 * i + 0];
    float v=inGeo.texcoords[2 * i + 1];
    inGeo.texcoords[2 * i + 0]=PApplet.min(1,u * uFactor);
    inGeo.texcoords[2 * i + 1]=PApplet.min(1,v * uFactor);
  }
  if (shapeCreated && tessellated && hasPolys) {
    int last1=0;
    if (is3D()) {
      last1=lastPolyVertex + 1;
    }
 else     if (is2D()) {
      last1=lastPolyVertex + 1;
      if (-1 < firstLineVertex)       last1=firstLineVertex;
      if (-1 < firstPointVertex)       last1=firstPointVertex;
    }
    for (int i=firstLineVertex; i < last1; i++) {
      float u=tessGeo.polyTexCoords[2 * i + 0];
      float v=tessGeo.polyTexCoords[2 * i + 1];
      tessGeo.polyTexCoords[2 * i + 0]=PApplet.min(1,u * uFactor);
      tessGeo.polyTexCoords[2 * i + 1]=PApplet.min(1,v * uFactor);
    }
    root.setModifiedPolyTexCoords(firstPolyVertex,last1 - 1);
  }
}
