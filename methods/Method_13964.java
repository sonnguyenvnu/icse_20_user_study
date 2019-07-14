@Override public void scrutinize(Value value){
  String str=null;
  if (MonolingualTextValue.class.isInstance(value)) {
    str=((MonolingualTextValue)value).getText();
  }
 else   if (StringValue.class.isInstance(value)) {
    str=((StringValue)value).getString();
  }
  if (str != null) {
    for (    Entry<String,Pattern> entry : _issuesMap.entrySet()) {
      if (entry.getValue().matcher(str).find()) {
        emitWarning(entry.getKey(),str);
      }
    }
  }
}
