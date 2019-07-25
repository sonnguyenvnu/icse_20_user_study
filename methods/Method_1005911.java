@NonNull @Override public List<ViewAttribute> collect(NavigationView view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("HeaderCount",view.getHeaderCount()));
  attributes.add(Collectors.createColorAttribute(view,"ItemTextColor",view.getItemTextColor()));
  attributes.add(Collectors.createColorAttribute(view,"ItemIconTint",view.getItemIconTintList()));
  attributes.add(new ViewAttribute<Void>("ItemBackground",view.getItemBackground()));
  return attributes;
}
