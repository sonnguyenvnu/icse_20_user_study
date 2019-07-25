public BeanPropertyWriter rename(NameTransformer transformer){
  String newName=transformer.transform(_name.getValue());
  if (newName.equals(_name.toString())) {
    return this;
  }
  return _new(PropertyName.construct(newName));
}
