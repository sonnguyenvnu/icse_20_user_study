@Override public UnwrappingBeanPropertyWriter rename(NameTransformer transformer){
  String oldName=_name.getValue();
  String newName=transformer.transform(oldName);
  transformer=NameTransformer.chainedTransformer(transformer,_nameTransformer);
  return _new(transformer,new SerializedString(newName));
}
