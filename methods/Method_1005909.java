@NonNull @Override public List<ViewAttribute> collect(CoordinatorLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("StatusBarBackground",view.getStatusBarBackground()));
  return attributes;
}
