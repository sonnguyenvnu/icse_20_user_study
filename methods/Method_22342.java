/** 
 * Adds all attachment uris into the given list
 * @param context     a context
 * @param reportText        the report content
 * @param attachments the target list
 * @return if the attachments contain the content
 */
protected boolean fillAttachmentList(@NonNull Context context,@NonNull String reportText,@NonNull List<Uri> attachments){
  final InstanceCreator instanceCreator=new InstanceCreator();
  attachments.addAll(instanceCreator.create(config.attachmentUriProvider(),DefaultAttachmentProvider::new).getAttachments(context,config));
  if (mailConfig.reportAsFile()) {
    final Uri report=createAttachmentFromString(context,mailConfig.reportFileName(),reportText);
    if (report != null) {
      attachments.add(report);
      return true;
    }
  }
  return false;
}
