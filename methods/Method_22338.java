/** 
 * Builds an intent used to resolve email clients and to send reports without attachments or as fallback if no attachments are supported
 * @param subject the message subject
 * @param body    the message body
 * @return email intent
 */
@NonNull protected Intent buildResolveIntent(@NonNull String subject,@NonNull String body){
  final Intent intent=new Intent(Intent.ACTION_SENDTO);
  intent.setData(Uri.fromParts("mailto",mailConfig.mailTo(),null));
  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
  intent.putExtra(Intent.EXTRA_SUBJECT,subject);
  intent.putExtra(Intent.EXTRA_TEXT,body);
  return intent;
}
