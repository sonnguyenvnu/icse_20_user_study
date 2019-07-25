@NonNull @Override public List<ViewAttribute> collect(TabLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("SelectedTabPosition",view.getSelectedTabPosition()));
  attributes.add(new ViewAttribute<>("TabCount",view.getTabCount()));
  attributes.add(new ViewAttribute<>("TabMode",new TabModeValue(view.getTabMode())));
  attributes.add(new ViewAttribute<>("TabGravity",new TabGravityValue(view.getTabGravity())));
  attributes.add(Collectors.createColorAttribute(view,"TabTextColor",view.getTabTextColors()));
  return attributes;
}
