@NonNull @Override public List<ViewAttribute> collect(final CompoundButton view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new MutableBooleanViewAttribute("Checked",view.isChecked()){
    @Override protected void mutate(    Boolean value){
      view.setChecked(value);
    }
  }
);
  attributes.add(new ViewAttribute<Void>("ButtonDrawable",CompoundButtonCompat.getButtonDrawable(view)));
  return attributes;
}
