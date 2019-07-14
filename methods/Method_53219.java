HttpParameter[] asPostParameterArray(){
  List<HttpParameter> list=asPostParameterList(SMCP,COUNT);
  if (list.size() == 0) {
    return NULL_PARAMETER_ARRAY;
  }
  return list.toArray(new HttpParameter[list.size()]);
}
