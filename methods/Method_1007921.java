public String replace(Matcher matcher){
  StringBuilder replacementBuffer=new StringBuilder();
  String replacementText;
  for (int group=1; group <= matcher.groupCount(); group++) {
    replacementText=getReplacement(group);
    if (replacementText != null) {
      replacementBuffer.append(replacementText);
    }
 else {
      replacementBuffer.append(matcher.group(group));
    }
  }
  return replacementBuffer.toString();
}
