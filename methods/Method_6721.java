public static boolean canPreviewDocument(TLRPC.Document document){
  if (document != null && document.mime_type != null) {
    String mime=document.mime_type.toLowerCase();
    if (isDocumentHasThumb(document) && (mime.equals("image/png") || mime.equals("image/jpg") || mime.equals("image/jpeg"))) {
      for (int a=0; a < document.attributes.size(); a++) {
        TLRPC.DocumentAttribute attribute=document.attributes.get(a);
        if (attribute instanceof TLRPC.TL_documentAttributeImageSize) {
          TLRPC.TL_documentAttributeImageSize size=(TLRPC.TL_documentAttributeImageSize)attribute;
          return size.w < 6000 && size.h < 6000;
        }
      }
    }
 else     if (BuildVars.DEBUG_PRIVATE_VERSION) {
      String fileName=FileLoader.getDocumentFileName(document);
      if (fileName.startsWith("tg_secret_sticker") && fileName.endsWith("json")) {
        return true;
      }
    }
  }
  return false;
}
