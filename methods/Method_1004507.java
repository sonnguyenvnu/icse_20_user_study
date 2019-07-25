private boolean validate(BaseMessage message){
  String subject=message.getSubject();
  if (Strings.isNullOrEmpty(subject))   return false;
  if (subject.contains("${"))   return false;
  return true;
}
