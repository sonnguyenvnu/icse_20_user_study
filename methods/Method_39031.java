/** 
 * Reads action path from class. If the class is annotated with  {@link MadvocAction} annotation,class action path will be read from annotation value. Otherwise, action class path will be built from the class name. This is done by removing the package name and the last contained word (if there is more then one) from the class name. Such name is finally uncapitalized.
 */
protected String[] readClassActionPath(final Class actionClass){
  MadvocAction madvocActionAnnotation=((Class<?>)actionClass).getAnnotation(MadvocAction.class);
  String classActionPath=madvocActionAnnotation != null ? madvocActionAnnotation.value().trim() : null;
  if (StringUtil.isEmpty(classActionPath)) {
    classActionPath=null;
  }
  String actionClassName=actionClass.getSimpleName();
  actionClassName=StringUtil.uncapitalize(actionClassName);
  actionClassName=MadvocUtil.stripLastCamelWord(actionClassName);
  if (classActionPath == null) {
    classActionPath=actionClassName;
  }
  return ArraysUtil.array(actionClassName,classActionPath);
}
