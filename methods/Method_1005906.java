@NonNull @Override public List<ViewAttribute> collect(AppBarLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("TotalScrollRange",view.getTotalScrollRange()));
  return attributes;
}
