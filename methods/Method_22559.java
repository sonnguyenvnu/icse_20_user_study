static public String pluralize(String key,int count){
  String customKey=key + "." + count;
  String value=get(customKey);
  if (value != null) {
    return String.format(value,count);
  }
  return interpolate(key + ".n",count);
}
