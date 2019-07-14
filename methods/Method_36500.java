@Override public JvmBinding convert(SofaReference sofaReferenceAnnotation,SofaReferenceBinding sofaReferenceBindingAnnotation,BindingConverterContext bindingConverterContext){
  if (JvmBinding.XmlConstants.BINDING_TYPE.equals(sofaReferenceBindingAnnotation.bindingType())) {
    JvmBindingParam jvmBindingParam=new JvmBindingParam();
    jvmBindingParam.setSerialize(sofaReferenceBindingAnnotation.serialize());
    return new JvmBinding().setJvmBindingParam(jvmBindingParam);
  }
  return null;
}
