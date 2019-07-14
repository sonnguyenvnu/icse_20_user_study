/** 
 * @param classSerializeFilters the classSerializeFilters to set
 */
public void setClassSerializeFilters(Map<Class<?>,SerializeFilter> classSerializeFilters){
  if (classSerializeFilters == null)   return;
  for (  Entry<Class<?>,SerializeFilter> entry : classSerializeFilters.entrySet())   this.serializeConfig.addFilter(entry.getKey(),entry.getValue());
  this.classSerializeFilters=classSerializeFilters;
}
