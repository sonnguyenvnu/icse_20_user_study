private static List<Integer> getLayoutValues(Element element,Class annotationClass){
  Annotation annotation=element.getAnnotation(annotationClass);
  List<Integer> layoutResources=new ArrayList<>();
  if (annotation instanceof EpoxyModelClass) {
    layoutResources.add(((EpoxyModelClass)annotation).layout());
  }
 else   if (annotation instanceof EpoxyDataBindingLayouts) {
    for (    int layoutRes : ((EpoxyDataBindingLayouts)annotation).value()) {
      layoutResources.add(layoutRes);
    }
  }
 else   if (annotation instanceof ModelView) {
    layoutResources.add(((ModelView)annotation).defaultLayout());
  }
  return layoutResources;
}
