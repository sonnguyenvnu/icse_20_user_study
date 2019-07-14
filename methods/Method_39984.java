/** 
 * Parses class annotations and adds all checks.
 * @see #resolveFor(Class)
 */
public void addClassChecks(final Class target){
  final List<Check> list=cache.get(target,() -> {
    final List<Check> newList=new ArrayList<>();
    final ClassDescriptor cd=ClassIntrospector.get().lookup(target);
    final PropertyDescriptor[] allProperties=cd.getAllPropertyDescriptors();
    for (    PropertyDescriptor propertyDescriptor : allProperties) {
      collectPropertyAnnotationChecks(newList,propertyDescriptor);
    }
    return newList;
  }
);
  addAll(list);
}
