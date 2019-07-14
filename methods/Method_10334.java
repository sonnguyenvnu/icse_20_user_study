protected String getParamString(){
  return URLEncodedUtils.format(getParamsList(),contentEncoding);
}
