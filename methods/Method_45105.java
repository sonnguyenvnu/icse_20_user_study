private ImmutableMap<String,String> doExtractForms(final HttpPostRequestDecoder decoder) throws IOException {
  List<InterfaceHttpData> bodyHttpDatas=decoder.getBodyHttpDatas();
  Map<String,String> forms=newHashMap();
  for (  InterfaceHttpData data : bodyHttpDatas) {
    if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
      Attribute attribute=(Attribute)data;
      forms.put(attribute.getName(),attribute.getValue());
    }
  }
  return copyOf(forms);
}
