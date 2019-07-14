private static TypeName[] getBounds(TypeParameterElement typeParameterElement){
  final TypeName[] bounds=new TypeName[typeParameterElement.getBounds().size()];
  for (int i=0, size=typeParameterElement.getBounds().size(); i < size; i++) {
    bounds[i]=TypeName.get(typeParameterElement.getBounds().get(i));
  }
  return bounds;
}
