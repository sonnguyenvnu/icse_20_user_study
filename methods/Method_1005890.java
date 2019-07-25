@NonNull @Override public List<ViewAttribute> collect(final EditText view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new MutableBooleanViewAttribute("FreezesText",view.getFreezesText()){
    @Override protected void mutate(    Boolean value){
      view.setFreezesText(value);
    }
  }
);
  return attributes;
}
