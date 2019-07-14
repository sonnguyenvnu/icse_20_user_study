/** 
 * Calculate the level of the target component. The level here means how many bracket pairs are needed to break until reaching the component type. For example, the level of  {@literal List<Component>} is 1, and the level of {@literal List<List<Component>>} is 2.
 * @return the level of the target component, or 0 if the target isn't a component.
 */
static int calculateLevelOfComponentInCollections(DeclaredTypeSpec typeSpec){
  int level=0;
  DeclaredTypeSpec declaredTypeSpec=typeSpec;
  while (declaredTypeSpec.isSubInterface(ClassNames.COLLECTION)) {
    Optional<DeclaredTypeSpec> result=declaredTypeSpec.getTypeArguments().stream().filter(it -> it != null && it instanceof DeclaredTypeSpec).findFirst().map(it -> (DeclaredTypeSpec)it);
    if (!result.isPresent()) {
      return 0;
    }
    declaredTypeSpec=result.get();
    level++;
  }
  return declaredTypeSpec.isSubType(ClassNames.COMPONENT) ? level : 0;
}
