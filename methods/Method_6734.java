public static int[] getWebDocumentWidthAndHeight(TLRPC.WebDocument document){
  if (document == null) {
    return null;
  }
  for (int a=0, size=document.attributes.size(); a < size; a++) {
    TLRPC.DocumentAttribute attribute=document.attributes.get(a);
    if (attribute instanceof TLRPC.TL_documentAttributeImageSize) {
      return new int[]{attribute.w,attribute.h};
    }
 else     if (attribute instanceof TLRPC.TL_documentAttributeVideo) {
      return new int[]{attribute.w,attribute.h};
    }
  }
  return null;
}
