public static String camelize(String input,boolean startWithLowerCase){
  String[] strings=org.apache.commons.lang.StringUtils.split(input.toLowerCase(),"_");
  for (int i=startWithLowerCase ? 1 : 0; i < strings.length; i++) {
    strings[i]=org.apache.commons.lang.StringUtils.capitalize(strings[i]);
  }
  input=org.apache.commons.lang.StringUtils.join(strings);
  if (!startWithLowerCase) {
    return ucfirst(input);
  }
  return input;
}
