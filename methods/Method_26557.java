/** 
 * Parameters which must be referenced in any tree matched to this placeholder. 
 */
Set<UVariableDecl> requiredParameters(){
  return Maps.filterValues(annotatedParameters(),(  ImmutableClassToInstanceMap<Annotation> annotations) -> !annotations.containsKey(MayOptionallyUse.class)).keySet();
}
