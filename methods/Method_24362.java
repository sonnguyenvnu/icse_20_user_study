protected void setSpecularImpl(int specular){
  if (specularColor == specular)   return;
  specularColor=specular;
  Arrays.fill(inGeo.specular,0,inGeo.vertexCount,PGL.javaToNativeARGB(specularColor));
  if (shapeCreated && tessellated && hasPolys) {
    if (is3D()) {
      Arrays.fill(tessGeo.polySpecular,firstPolyVertex,lastPolyVertex + 1,PGL.javaToNativeARGB(specularColor));
      root.setModifiedPolySpecular(firstPolyVertex,lastPolyVertex);
    }
 else     if (is2D()) {
      int last1=lastPolyVertex + 1;
      if (-1 < firstLineVertex)       last1=firstLineVertex;
      if (-1 < firstPointVertex)       last1=firstPointVertex;
      Arrays.fill(tessGeo.polySpecular,firstPolyVertex,last1,PGL.javaToNativeARGB(specularColor));
      root.setModifiedPolyColors(firstPolyVertex,last1 - 1);
    }
  }
}
