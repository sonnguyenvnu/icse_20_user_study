/** 
 * Get the prop defaults from the given  {@link TypeElement}.
 */
public static ImmutableList<PropDefaultModel> getPropDefaults(TypeElement typeElement){
  final List<PropDefaultModel> propDefaults=new ArrayList<>();
  final List<? extends Element> enclosedElements=typeElement.getEnclosedElements();
  for (  Element enclosedElement : enclosedElements) {
    propDefaults.addAll(extractFromField(enclosedElement));
    propDefaults.addAll(extractFromMethod(enclosedElement));
  }
  return ImmutableList.copyOf(propDefaults);
}
