@NonNull @Override public List<ViewAttribute> collect(final FrameLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new MutableBooleanViewAttribute("MeasureAllChildren",view.getMeasureAllChildren()){
    @Override protected void mutate(    Boolean value) throws Exception {
      view.setMeasureAllChildren(value);
    }
  }
);
  return attributes;
}
