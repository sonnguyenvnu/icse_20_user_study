protected void setShininessImpl(float shininess){
  if (PGraphicsOpenGL.same(this.shininess,shininess))   return;
  this.shininess=shininess;
  Arrays.fill(inGeo.shininess,0,inGeo.vertexCount,shininess);
  if (shapeCreated && tessellated && hasPolys) {
    if (is3D()) {
      Arrays.fill(tessGeo.polyShininess,firstPolyVertex,lastPolyVertex + 1,shininess);
      root.setModifiedPolyShininess(firstPolyVertex,lastPolyVertex);
    }
 else     if (is2D()) {
      int last1=lastPolyVertex + 1;
      if (-1 < firstLineVertex)       last1=firstLineVertex;
      if (-1 < firstPointVertex)       last1=firstPointVertex;
      Arrays.fill(tessGeo.polyShininess,firstPolyVertex,last1,shininess);
      root.setModifiedPolyColors(firstPolyVertex,last1 - 1);
    }
  }
}
