private final static BeanPropertyWriter[] rename(BeanPropertyWriter[] props,NameTransformer transformer){
  if (props == null || props.length == 0 || transformer == null || transformer == NameTransformer.NOP) {
    return props;
  }
  final int len=props.length;
  BeanPropertyWriter[] result=new BeanPropertyWriter[len];
  for (int i=0; i < len; ++i) {
    BeanPropertyWriter bpw=props[i];
    if (bpw != null) {
      result[i]=bpw.rename(transformer);
    }
  }
  return result;
}
