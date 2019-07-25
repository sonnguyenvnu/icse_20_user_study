@NonNull @Override public List<ViewAttribute> collect(final TextView view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new MutableStringViewAttribute("Text",view.getText().toString()){
    @Override protected void mutate(    CharSequence value){
      view.setText(value);
    }
  }
);
  attributes.add(new MutableStringViewAttribute("Hint",view.getHint()){
    @Override protected void mutate(    CharSequence value){
      view.setHint(value);
    }
  }
);
  attributes.add(new ViewAttribute<>("TextColor",new ColorValue(view.getCurrentTextColor()),new ColorDrawable(view.getCurrentTextColor())));
  attributes.add(new ViewAttribute<>("HintColor",new ColorValue(view.getCurrentHintTextColor()),new ColorDrawable(view.getCurrentHintTextColor())));
  attributes.add(new ViewAttribute<>("Typeface",view.getTypeface()));
  attributes.add(new ViewAttribute<>("TextSize",attributeTranslator.translatePxToSp((int)view.getTextSize())));
  attributes.add(new ViewAttribute<>("AutoSizeMaxTextSize",TextViewCompat.getAutoSizeMaxTextSize(view)));
  attributes.add(new ViewAttribute<>("AutoSizeMinTextSize",TextViewCompat.getAutoSizeMinTextSize(view)));
  attributes.add(new ViewAttribute<>("AutoSizeStepGranularity",TextViewCompat.getAutoSizeStepGranularity(view)));
  attributes.add(new ViewAttribute<>("Gravity",new GravityValue(view.getGravity())));
  attributes.add(new ViewAttribute<>("ImeAction",view.getImeActionId()));
  attributes.add(new ViewAttribute<>("ImeActionLabel",view.getImeActionLabel()));
  attributes.add(new ViewAttribute<>("ImeOptions",new ImeOptionsValue(view.getImeOptions())));
  attributes.add(new ViewAttribute<>("CompoundPaddingLeft",attributeTranslator.translatePx(view.getCompoundPaddingLeft())));
  attributes.add(new ViewAttribute<>("CompoundPaddingTop",attributeTranslator.translatePx(view.getCompoundPaddingTop())));
  attributes.add(new ViewAttribute<>("CompoundPaddingRight",attributeTranslator.translatePx(view.getCompoundPaddingRight())));
  attributes.add(new ViewAttribute<>("CompoundPaddingBottom",attributeTranslator.translatePx(view.getCompoundPaddingBottom())));
  attributes.add(new ViewAttribute<>("CompoundDrawable",attributeTranslator.translatePx(view.getCompoundDrawablePadding())));
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
    attributes.add(Collectors.createColorAttribute(view,"CompoundDrawableTint",view.getCompoundDrawableTintList()));
    attributes.add(new ViewAttribute<>("CompoundDrawableTintMode",new PorterDuffModeValue(view.getCompoundDrawableTintMode())));
  }
  return attributes;
}
