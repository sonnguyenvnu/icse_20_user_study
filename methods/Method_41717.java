public static String createUniqueName(String group){
  if (group == null)   group=DEFAULT_GROUP;
  String n1=UUID.randomUUID().toString();
  String n2=UUID.nameUUIDFromBytes(group.getBytes()).toString();
  return String.format("%s-%s",n2.substring(24),n1);
}
