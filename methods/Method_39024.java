/** 
 * Detects if alias is defined in annotation and registers it if so.
 */
protected void detectAndRegisterAlias(final ActionAnnotationValues annotationValues,final ActionDefinition actionDefinition){
  final String alias=parseMethodAlias(annotationValues);
  if (alias != null) {
    String aliasPath=StringUtil.cutToIndexOf(actionDefinition.actionPath(),StringPool.HASH);
    actionsManager.registerPathAlias(alias,aliasPath);
  }
}
