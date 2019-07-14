/** 
 * method converts bash style regular expression to java. See  {@link Pattern}
 * @return converted string
 */
private String bashRegexToJava(String originalString){
  StringBuilder stringBuilder=new StringBuilder();
  for (int i=0; i < originalString.length(); i++) {
switch (originalString.charAt(i) + "") {
case "*":
      stringBuilder.append("\\w*");
    break;
case "?":
  stringBuilder.append("\\w");
break;
default :
stringBuilder.append(originalString.charAt(i));
break;
}
}
Log.d(getClass().getSimpleName(),stringBuilder.toString());
return stringBuilder.toString();
}
