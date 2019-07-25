protected List<Object> parse(String string){
  List<Object> template=new ArrayList<Object>();
  while (string.contains("{")) {
    int startPattern=string.indexOf("{");
    template.add(string.substring(0,startPattern));
    int endPattern=string.indexOf("}");
    Assert.isTrue(endPattern > startPattern + 1,"Invalid pattern given " + string);
    String nestedString=string.substring(startPattern + 1,endPattern);
    int separator=nestedString.indexOf(FORMAT_SEPARATOR);
    if (separator > 0) {
      Assert.isTrue(nestedString.length() > separator + 1,"Invalid format given " + nestedString);
      String format=nestedString.substring(separator + 1);
      nestedString=nestedString.substring(0,separator);
      template.add(wrapWithFormatter(format,createFieldExtractor(nestedString)));
    }
 else {
      template.add(createFieldExtractor(nestedString));
    }
    string=string.substring(endPattern + 1).trim();
  }
  if (StringUtils.hasText(string)) {
    template.add(string);
  }
  return template;
}
