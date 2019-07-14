public String getKey(){
  String key="";
  while (count > 0) {
    count--;
    key+=characters.charAt(count);
    count/=characters.length();
  }
  return key;
}
