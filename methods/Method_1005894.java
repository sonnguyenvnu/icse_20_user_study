@NonNull @Override public List<ViewAttribute> collect(RelativeLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    attributes.add(new ViewAttribute<>("Gravity",new GravityValue(view.getGravity())));
  }
  return attributes;
}
