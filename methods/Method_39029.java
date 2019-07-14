/** 
 * Reads class or method annotation for action filters.
 */
protected Class<? extends ActionFilter>[] readActionFilters(final AnnotatedElement actionClassOrMethod){
  Class<? extends ActionFilter>[] result=null;
  FilteredBy filteredBy=actionClassOrMethod.getAnnotation(FilteredBy.class);
  if (filteredBy != null) {
    result=filteredBy.value();
    if (result.length == 0) {
      result=null;
    }
  }
  return result;
}
