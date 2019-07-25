@NonNull @Override public List<ViewAttribute> collect(FloatingActionButton view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("RippleColor",new ColorValue(view.getRippleColor())));
  attributes.add(new ViewAttribute<>("UseCompatPadding",view.getUseCompatPadding()));
  attributes.add(new ViewAttribute<>("Size",new SizeValue(view.getSize())));
  attributes.add(new ViewAttribute<Void>("ContentBackground",view.getContentBackground()));
  attributes.add(new ViewAttribute<>("CompatElevation",view.getCompatElevation()));
  return attributes;
}
