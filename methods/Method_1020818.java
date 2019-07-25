public static String pluralize(String input){
  if (irregular.containsKey(input)) {
    return irregular.get(input);
  }
 else {
    String finalResult=input;
    for (    Map.Entry<String,Pattern> entry : pluralPatternMap.entrySet()) {
      StringBuffer stringBuffer=new StringBuffer();
      Pattern temp=entry.getValue();
      Matcher matcher=temp.matcher(input);
      String key=entry.getKey();
      while (matcher.find()) {
        matcher.appendReplacement(stringBuffer,entry.getKey());
      }
      matcher.appendTail(stringBuffer);
      if (!stringBuffer.toString().equals(input)) {
        finalResult=stringBuffer.toString();
      }
    }
    return finalResult;
  }
}
