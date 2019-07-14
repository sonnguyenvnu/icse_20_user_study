/** 
 * Looks for an element with the given tag name in the Tree data being parsed, returning the path hierarchy to reach it.
 * @param parser
 * @param tag The element name (can be qualified) to search for
 * @return If the tag is found, an array of strings is returned. If the tag is at the top level, the tag will be the only item in the array. If the tag is nested beneath the top level, the array is filled with the hierarchy with the tag name at the last index null if the the tag is not found.
 * @throws ServletException
 */
static protected List<String> detectRecordElement(TreeReader parser,String tag) throws TreeReaderException {
  if (parser.current() == Token.Ignorable) {
    parser.next();
  }
  String localName=parser.getFieldName();
  String fullName=composeName(parser.getPrefix(),localName);
  if (tag.equals(parser.getFieldName()) || tag.equals(fullName)) {
    List<String> path=new LinkedList<String>();
    path.add(localName);
    return path;
  }
  while (parser.hasNext()) {
    Token eventType=parser.next();
    if (eventType == Token.EndEntity) {
      break;
    }
 else     if (eventType == Token.StartEntity) {
      List<String> path=detectRecordElement(parser,tag);
      if (path != null) {
        path.add(0,localName);
        return path;
      }
    }
  }
  return null;
}
