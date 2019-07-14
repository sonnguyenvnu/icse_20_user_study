/** 
 * @param rClass        Class name for a resource, like R.layout
 * @param resourceClass The class element representing that resource eg the R.layout class
 */
private void saveResourceValuesForRClass(ClassName rClass,Element resourceClass){
  if (rClassResources.containsKey(rClass)) {
    return;
  }
  List<? extends Element> resourceElements=resourceClass.getEnclosedElements();
  List<ResourceValue> resourceNames=new ArrayList<>(resourceElements.size());
  for (  Element resource : resourceElements) {
    if (!(resource instanceof VariableElement)) {
      continue;
    }
    String resourceName=resource.getSimpleName().toString();
    resourceNames.add(new ResourceValue(rClass,resourceName,0));
  }
  rClassResources.put(rClass,resourceNames);
}
