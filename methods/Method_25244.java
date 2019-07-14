@Override Nullness visitTypeCast(TypeCastNode node,SubNodeValues inputs){
  ImmutableList<String> annotations=node.getType().getAnnotationMirrors().stream().map(Object::toString).collect(ImmutableList.toImmutableList());
  return NullnessAnnotations.fromAnnotations(annotations).orElseGet(() -> hasPrimitiveType(node) ? NONNULL : inputs.valueOfSubNode(node.getOperand()));
}
