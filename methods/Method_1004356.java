public static String build(String subjectId,String messageId){
  if (Strings.isNullOrEmpty(subjectId))   throw new RuntimeException("Subject Needed.");
  StringBuilder builder=new StringBuilder();
  if (!Strings.isNullOrEmpty(messageId)) {
    builder.append("^").append(subjectId).append("\\d{12}").append(generateMD5Key(messageId));
  }
 else {
    builder.append("^").append(subjectId).append("\\d{12}").append("\\w{32}");
  }
  return builder.toString();
}
