public static Document convert2Document(File file){
  Document document=Document.create(file);
  if (document != null) {
    return document;
  }
 else {
    throw new IllegalArgumentException(file.getPath() + "????");
  }
}
