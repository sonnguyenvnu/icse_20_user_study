/** 
 * find a parser for a given url and mime type because mime types returned by web severs are sometimes wrong, we also compute the mime type again from the extension that can be extracted from the url path. That means that there are 3 criteria that can be used to select a parser: - the given mime type (1.) - the extension of url (2.) - the mime type computed from the extension (3.) finally the generic parser is added as backup if all above fail
 * @param url the given url
 * @param mimeType the given mime type
 * @return a list of Idiom parsers that may be appropriate for the given criteria
 * @throws Parser.Failure
 */
private static Set<Parser> parsers(final MultiProtocolURL url,String mimeType1) throws Parser.Failure {
  final Set<Parser> idioms=new LinkedHashSet<Parser>(2);
  Set<Parser> idiom;
  if (mimeType1 != null) {
    mimeType1=normalizeMimeType(mimeType1);
    if (denyMime.containsKey(mimeType1))     throw new Parser.Failure("mime type '" + mimeType1 + "' is denied (1)",url);
    idiom=mime2parser.get(mimeType1);
    if (idiom != null)     idioms.addAll(idiom);
  }
  String ext=MultiProtocolURL.getFileExtension(url.getFileName());
  if (ext != null && ext.length() > 0) {
    if (denyExtensionx.containsKey(ext))     throw new Parser.Failure("file extension '" + ext + "' is denied (1)",url);
    idiom=ext2parser.get(ext);
    if (idiom != null && !idioms.containsAll(idiom)) {
      idioms.addAll(idiom);
    }
  }
  final String mimeType2=ext2mime.get(ext);
  if (mimeType2 != null && (idiom=mime2parser.get(mimeType2)) != null && !idioms.containsAll(idiom)) {
    idioms.addAll(idiom);
  }
  idioms.add(genericIdiom);
  return idioms;
}
