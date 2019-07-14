public static WebFile createWithWebDocument(TLRPC.WebDocument webDocument){
  if (!(webDocument instanceof TLRPC.TL_webDocument)) {
    return null;
  }
  WebFile webFile=new WebFile();
  TLRPC.TL_webDocument document=(TLRPC.TL_webDocument)webDocument;
  TLRPC.TL_inputWebFileLocation location=new TLRPC.TL_inputWebFileLocation();
  webFile.location=location;
  location.url=webFile.url=webDocument.url;
  location.access_hash=document.access_hash;
  webFile.size=document.size;
  webFile.mime_type=document.mime_type;
  webFile.attributes=document.attributes;
  return webFile;
}
