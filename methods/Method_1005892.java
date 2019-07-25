@NonNull @Override public List<ViewAttribute> collect(ImageView view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute("Image",view.getDrawable()));
  attributes.add(new ViewAttribute<>("ScaleType",view.getScaleType().toString()));
  return attributes;
}
