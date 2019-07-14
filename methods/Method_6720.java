public static boolean isDocumentHasThumb(TLRPC.Document document){
  if (document == null || document.thumbs.isEmpty()) {
    return false;
  }
  for (int a=0, N=document.thumbs.size(); a < N; a++) {
    TLRPC.PhotoSize photoSize=document.thumbs.get(a);
    if (photoSize != null && !(photoSize instanceof TLRPC.TL_photoSizeEmpty) && !(photoSize.location instanceof TLRPC.TL_fileLocationUnavailable)) {
      return true;
    }
  }
  return false;
}
