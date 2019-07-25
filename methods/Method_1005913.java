@NonNull @Override public List<ViewAttribute> collect(TextInputLayout view,AttributeTranslator attributeTranslator){
  List<ViewAttribute> attributes=new ArrayList<>();
  attributes.add(new ViewAttribute<>("PasswordToggleDrawable",view.getPasswordVisibilityToggleDrawable()));
  attributes.add(new ViewAttribute<>("PasswordToggleContentDisc",nonNull(view.getPasswordVisibilityToggleContentDescription())));
  attributes.add(new ViewAttribute<>("CounterMaxLength",view.getCounterMaxLength()));
  attributes.add(new ViewAttribute<>("Hint",nonNull(view.getHint())));
  attributes.add(new ViewAttribute<>("CounterEnabled",view.isCounterEnabled()));
  attributes.add(new ViewAttribute<>("ErrorEnabled",view.isErrorEnabled()));
  attributes.add(new ViewAttribute<>("HintAnimationEnabled",view.isHintAnimationEnabled()));
  attributes.add(new ViewAttribute<>("HintEnabled",view.isHintEnabled()));
  attributes.add(new ViewAttribute<>("PasswordToggleEnabled",view.isPasswordVisibilityToggleEnabled()));
  return attributes;
}
