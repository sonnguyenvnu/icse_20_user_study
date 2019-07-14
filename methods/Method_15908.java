protected Object appendCondition(List<Object> values,String wherePrefix,SqlAppender appender){
  int len=values.size();
  if (len == 1) {
    appender.add("=#{",wherePrefix,".value.value[0]}");
  }
 else {
    appender.add("in(");
    for (int i=0; i < len; i++) {
      if (i > 0) {
        appender.add(",");
      }
      appender.add("#{",wherePrefix,".value.value[" + i + "]}");
    }
    appender.add(")");
  }
  return values;
}
