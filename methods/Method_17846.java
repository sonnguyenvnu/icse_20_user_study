/** 
 * Creates a new ComponentContext instance scoped to the given component and sets it on the component.
 * @param context context scoped to the parent component
 * @param scope component associated with the newly created scoped context
 * @return a new ComponentContext instance scoped to the given component
 */
@VisibleForTesting(otherwise=VisibleForTesting.PACKAGE_PRIVATE) public static ComponentContext withComponentScope(ComponentContext context,Component scope){
  ComponentContext componentContext=context.makeNewCopy();
  componentContext.mComponentScope=scope;
  componentContext.mComponentTree=context.mComponentTree;
  return componentContext;
}
