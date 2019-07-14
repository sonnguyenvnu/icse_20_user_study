public String getMusicTitle(boolean unknown){
  TLRPC.Document document=getDocument();
  if (document != null) {
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        if (attribute.voice) {
          if (!unknown) {
            return null;
          }
          return LocaleController.formatDateAudio(messageOwner.date);
        }
        String title=attribute.title;
        if (title == null || title.length() == 0) {
          title=FileLoader.getDocumentFileName(document);
          if (TextUtils.isEmpty(title) && unknown) {
            title=LocaleController.getString("AudioUnknownTitle",R.string.AudioUnknownTitle);
          }
        }
        return title;
      }
 else       if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
        if (attribute.round_message) {
          return LocaleController.formatDateAudio(messageOwner.date);
        }
      }
    }
    String fileName=FileLoader.getDocumentFileName(document);
    if (!TextUtils.isEmpty(fileName)) {
      return fileName;
    }
  }
  return LocaleController.getString("AudioUnknownTitle",R.string.AudioUnknownTitle);
}
