public String getMimeType(){
  if (messageOwner.media instanceof TLRPC.TL_messageMediaDocument) {
    return messageOwner.media.document.mime_type;
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaInvoice) {
    TLRPC.WebDocument photo=((TLRPC.TL_messageMediaInvoice)messageOwner.media).photo;
    if (photo != null) {
      return photo.mime_type;
    }
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaPhoto) {
    return "image/jpeg";
  }
 else   if (messageOwner.media instanceof TLRPC.TL_messageMediaWebPage) {
    if (messageOwner.media.webpage.document != null) {
      return messageOwner.media.document.mime_type;
    }
 else     if (messageOwner.media.webpage.photo != null) {
      return "image/jpeg";
    }
  }
  return "";
}
