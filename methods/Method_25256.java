private Nullness returnValueNullness(MethodInvocationNode node,@Nullable ClassAndMethod callee){
  if (callee == null) {
    return defaultAssumption;
  }
  Optional<Nullness> declaredNullness=NullnessAnnotations.fromAnnotations(callee.annotations);
  if (declaredNullness.isPresent()) {
    return declaredNullness.get();
  }
  if (AccessPath.isAutoValueAccessor(node.getTree())) {
    return NONNULL;
  }
  Nullness assumedNullness=methodReturnsNonNull.apply(callee) ? NONNULL : NULLABLE;
  if (!callee.isGenericResult) {
    return assumedNullness;
  }
  return getInferredNullness(node).orElse(assumedNullness);
}
