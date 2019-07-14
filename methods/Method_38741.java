/** 
 * Invoked on each property. Properties are getting matched against the rules. If property passes all the rules, it will be processed in {@link #onSerializableProperty(String,jodd.introspector.PropertyDescriptor)}.
 */
protected void onProperty(String propertyName,final PropertyDescriptor propertyDescriptor,final boolean isTransient){
  Class propertyType=propertyDescriptor == null ? null : propertyDescriptor.getType();
  Path currentPath=jsonContext.path;
  currentPath.push(propertyName);
  if (propertyType != null) {
    propertyName=typeData.resolveJsonName(propertyName);
  }
  boolean include=!typeData.strict;
  if (isTransient) {
    include=false;
  }
  include=jsonContext.matchIgnoredPropertyTypes(propertyType,true,include);
  include=typeData.rules.apply(propertyName,true,include);
  include=jsonContext.matchPathToQueries(include);
  if (!include) {
    currentPath.pop();
    return;
  }
  onSerializableProperty(propertyName,propertyDescriptor);
  currentPath.pop();
}
