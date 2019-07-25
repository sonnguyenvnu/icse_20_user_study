@Override public void configure(AnnotatedType annotatedType){
  super.configure(annotatedType);
  List<AnnotatedType> annotatedComponentTypes=annotatedComponentTypes(annotatedType);
  if (annotatedComponentTypes.size() == components.size()) {
    for (int i=0; i < components.size(); ++i)     components.get(i).configure(annotatedComponentTypes.get(i));
  }
}
