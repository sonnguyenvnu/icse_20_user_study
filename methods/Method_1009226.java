private static void attachments(@NotNull StringBuilder stringBuilder,int level,List<Attachment> attachmentList){
  if (!attachmentList.isEmpty()) {
    header(stringBuilder,level,"Attachments");
    for (    Attachment attachment : attachmentList) {
      attachment(stringBuilder,level + 1,attachment);
    }
  }
}
