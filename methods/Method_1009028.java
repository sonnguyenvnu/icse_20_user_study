private ContentType map(Format f){
  if (Format.DOCX.equals(f)) {
    return ContentType.create("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
  }
 else   if (Format.DOC.equals(f)) {
    return ContentType.create("application/msword");
  }
  return null;
}
