/** 
 * This attempts to extract a prop-default from a <em>method</em>. This is only necessary for Kotlin KAPT generated code, which currently does a rather strange thing where it generates a method <code>void field_name$annotations()</code> for every <code>field_name</code> that has all annotations for said field. <p>So, if we find a method that looks like this, and it contains a <code>PropDefault</code> annotation, we will try to find a matching field of this name and add use it as basis for our prop-default.
 */
private static ImmutableList<PropDefaultModel> extractFromMethod(Element enclosedElement){
  if (enclosedElement.getKind() != ElementKind.METHOD) {
    return ImmutableList.of();
  }
  final ExecutableElement methodElement=(ExecutableElement)enclosedElement;
  final Annotation propDefaultAnnotation=methodElement.getAnnotation(PropDefault.class);
  if (propDefaultAnnotation == null) {
    return ImmutableList.of();
  }
  final String methodName=methodElement.getSimpleName().toString();
  boolean isPropDefaultWithoutGet=methodName.endsWith("$annotations") && methodElement.getReturnType().getKind() == TypeKind.VOID;
  final String baseName;
  if (isPropDefaultWithoutGet) {
    baseName=methodName.subSequence(0,methodName.indexOf('$')).toString();
  }
 else {
    baseName=methodName.replaceFirst("get","").substring(0,1).toLowerCase() + methodName.replaceFirst("get","").substring(1);
  }
  final Optional<? extends Element> element=enclosedElement.getEnclosingElement().getEnclosedElements().stream().filter(e -> e.getSimpleName().toString().equals(baseName)).findFirst();
  final ResType propDefaultResType=((PropDefault)propDefaultAnnotation).resType();
  final int propDefaultResId=((PropDefault)propDefaultAnnotation).resId();
  return element.map(e -> ImmutableList.of(new PropDefaultModel(TypeName.get(e.asType()),baseName,ImmutableList.copyOf(new ArrayList<>(methodElement.getModifiers())),methodElement,propDefaultResType,propDefaultResId))).orElseGet(ImmutableList::of);
}
