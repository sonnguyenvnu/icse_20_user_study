@NonNull @Override public List<ViewAttribute> collect(final LinearLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("Orientation",new OrientationValue(view.getOrientation())));
  attributes.add(new ViewAttribute<>("WeightSum",view.getWeightSum()));
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
    attributes.add(new ViewAttribute<>("Gravity",new GravityValue(view.getGravity())));
  }
  attributes.add(new ViewAttribute<>("ShowDividers",new DividerModeValue(view.getShowDividers())));
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
    attributes.add(new ViewAttribute<Void>("Divider",view.getDividerDrawable()));
  }
  attributes.add(new ViewAttribute<>("DividerPadding",attributeTranslator.translatePx(view.getDividerPadding())));
  attributes.add(new MutableBooleanViewAttribute("MeasureWithLargestChild",view.isMeasureWithLargestChildEnabled()){
    @Override protected void mutate(    Boolean value) throws Exception {
      view.setMeasureWithLargestChildEnabled(value);
    }
  }
);
  attributes.add(new MutableBooleanViewAttribute("BaselineAligned",view.isBaselineAligned()){
    @Override protected void mutate(    Boolean value) throws Exception {
      view.setBaselineAligned(value);
    }
  }
);
  attributes.add(new MutableStringViewAttribute("BaselineAlignedChildIndex",String.valueOf(view.getBaselineAlignedChildIndex())){
    @Override protected void mutate(    CharSequence value) throws Exception {
      int index=Integer.parseInt(value.toString());
      view.setBaselineAlignedChildIndex(index);
    }
  }
);
  return attributes;
}
