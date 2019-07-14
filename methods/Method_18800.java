static TypeSpecDataHolder generateTreeProps(SpecModel specModel){
  final TypeSpecDataHolder.Builder typeSpecDataHolder=TypeSpecDataHolder.newBuilder();
  final ImmutableList<TreePropModel> treeProps=specModel.getTreeProps();
  for (  TreePropModel treeProp : treeProps) {
    typeSpecDataHolder.addField(FieldSpec.builder(treeProp.getTypeName(),treeProp.getName()).addAnnotation(TreeProp.class).addAnnotation(AnnotationSpec.builder(Comparable.class).addMember("type","$L",getComparableType(specModel,treeProp)).build()).build());
  }
  return typeSpecDataHolder.build();
}
