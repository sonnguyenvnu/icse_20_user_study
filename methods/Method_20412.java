/** 
 * Returns a list of layout resources whose name contains the given layout as a prefix.
 */
List<ResourceValue> getAlternateLayouts(ResourceValue layout){
  if (rClassResources.isEmpty()) {
    Element rLayoutClassElement=getElementByName(layout.getClassName(),elementUtils,typeUtils);
    saveResourceValuesForRClass(layout.getClassName(),rLayoutClassElement);
  }
  List<ResourceValue> layouts=rClassResources.get(layout.getClassName());
  if (layouts == null) {
    errorLogger.logError("No layout files found for R class: %s",layout.getClassName());
    return Collections.emptyList();
  }
  List<ResourceValue> result=new ArrayList<>();
  String target=layout.getResourceName() + "_";
  for (  ResourceValue otherLayout : layouts) {
    if (otherLayout.getResourceName().startsWith(target)) {
      result.add(otherLayout);
    }
  }
  return result;
}
