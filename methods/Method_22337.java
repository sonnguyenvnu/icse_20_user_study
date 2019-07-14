/** 
 * Builds an email intent with attachments
 * @param subject         the message subject
 * @param body            the message body
 * @param attachments     the attachments
 * @return email intent
 */
@NonNull protected Intent buildAttachmentIntent(@NonNull String subject,@Nullable String body,@NonNull ArrayList<Uri> attachments){
  final Intent intent=new Intent(Intent.ACTION_SEND_MULTIPLE);
  intent.putExtra(Intent.EXTRA_EMAIL,new String[]{mailConfig.mailTo()});
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  intent.putExtra(Intent.EXTRA_SUBJECT,subject);
  intent.setType("message/rfc822");
  intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM,attachments);
  intent.putExtra(Intent.EXTRA_TEXT,body);
  return intent;
}
