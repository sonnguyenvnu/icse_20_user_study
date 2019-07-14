/** 
 * Creates the message subject
 * @param context a context
 * @return the message subject
 */
@NonNull protected String buildSubject(@NonNull Context context){
  final String subject=mailConfig.subject();
  if (subject != null) {
    return subject;
  }
  return context.getPackageName() + " Crash Report";
}
