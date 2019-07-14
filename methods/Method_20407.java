/** 
 * Get detailed information about the layout resources that are parameters to the given annotation.
 */
List<ResourceValue> getLayoutsInAnnotation(Element element,Class annotationClass){
  List<Integer> layoutValues=getLayoutValues(element,annotationClass);
  return getResourcesInAnnotation(element,annotationClass,"layout",layoutValues);
}
