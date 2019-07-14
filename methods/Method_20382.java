@Override public Set<String> getSupportedAnnotationTypes(){
  Set<String> types=new LinkedHashSet<>();
  types.add(EpoxyModelClass.class.getCanonicalName());
  types.add(EpoxyAttribute.class.getCanonicalName());
  types.add(PackageEpoxyConfig.class.getCanonicalName());
  types.add(AutoModel.class.getCanonicalName());
  types.add(EpoxyDataBindingLayouts.class.getCanonicalName());
  types.add(EpoxyDataBindingPattern.class.getCanonicalName());
  types.add(ModelView.class.getCanonicalName());
  types.add(PackageModelViewConfig.class.getCanonicalName());
  types.add(TextProp.class.getCanonicalName());
  types.add(CallbackProp.class.getCanonicalName());
  types.add(ClassNames.LITHO_ANNOTATION_LAYOUT_SPEC.reflectionName());
  return types;
}
