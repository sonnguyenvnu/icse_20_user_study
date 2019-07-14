/** 
 * Try to match potential type names against the code graph. If any match, graph references and styles are added for them.
 */
private void scanCommentForTypeNames(){
  Matcher m=TYPE_NAME.matcher(docString);
  while (m.find()) {
    String qname=m.group();
    int beg=m.start() + docOffset;
    if (offsets.contains(beg)) {
      continue;
    }
    if (qname.length() < MIN_TYPE_NAME_LENGTH) {
      continue;
    }
    checkForReference(beg,qname);
  }
}
