private static ImmutableSet<Tree.Kind> supportedTreeTypes(Element whitelistAnnotation){
  Target targetAnnotation=whitelistAnnotation.getAnnotation(Target.class);
  if (targetAnnotation == null) {
    return TREE_TYPE_UNKNOWN_ANNOTATION;
  }
  ImmutableSet.Builder<Tree.Kind> types=ImmutableSet.builder();
  for (  ElementType t : targetAnnotation.value()) {
switch (t) {
case TYPE:
      types.add(Tree.Kind.CLASS,Tree.Kind.ENUM,Tree.Kind.INTERFACE,Tree.Kind.ANNOTATION_TYPE);
    break;
case METHOD:
  types.add(Tree.Kind.METHOD);
break;
default :
break;
}
}
return types.build();
}
