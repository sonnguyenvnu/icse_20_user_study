/** 
 * Detects  {@link jodd.madvoc.meta.ActionAnnotationValues}. Returns  {@code null} if annotation does not exist.
 */
protected ActionAnnotationValues detectActionAnnotationValues(final Method actionMethod){
  return actionConfigManager.readAnnotationValue(actionMethod);
}
