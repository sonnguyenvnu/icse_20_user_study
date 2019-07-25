@Override protected TokenStream normalize(String fieldName,TokenStream in){
  TokenStream stream=in;
  if (lowercase) {
    stream=new LowerCaseFilter(stream);
  }
  return stream;
}
