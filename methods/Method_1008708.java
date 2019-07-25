public BytesRef join(BytesRef separator,BytesRef preTag,BytesRef postTag){
  return join(separator,new BytesRefBuilder(),preTag,postTag);
}
