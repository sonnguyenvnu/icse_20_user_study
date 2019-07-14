public static boolean isMusicDocument(TLRPC.Document document){
  if (document != null) {
    for (int a=0; a < document.attributes.size(); a++) {
      TLRPC.DocumentAttribute attribute=document.attributes.get(a);
      if (attribute instanceof TLRPC.TL_documentAttributeAudio) {
        return !attribute.voice;
      }
    }
    if (!TextUtils.isEmpty(document.mime_type)) {
      String mime=document.mime_type.toLowerCase();
      if (mime.equals("audio/flac") || mime.equals("audio/ogg") || mime.equals("audio/opus") || mime.equals("audio/x-opus+ogg")) {
        return true;
      }
 else       if (mime.equals("application/octet-stream") && FileLoader.getDocumentFileName(document).endsWith(".opus")) {
        return true;
      }
    }
  }
  return false;
}
