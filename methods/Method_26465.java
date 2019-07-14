static TypeParameterTree typeParameterInList(List<? extends TypeParameterTree> typeParameters,Symbol v){
  return typeParameters.stream().filter(t -> t.getName().contentEquals(v.name)).collect(MoreCollectors.onlyElement());
}
