protected Object list(String fieldName,String fieldMapping,Parser parser){
  Token t=parser.currentToken();
  if (t == null) {
    t=parser.nextToken();
  }
  if (t == Token.START_ARRAY) {
    t=parser.nextToken();
  }
  Object array=reader.createArray(mapping(fieldMapping,parser));
  List<Object> content=new ArrayList<Object>(1);
  for (; parser.currentToken() != Token.END_ARRAY; ) {
    content.add(readListItem(fieldName,parser.currentToken(),fieldMapping,parser));
  }
  parser.nextToken();
  array=reader.addToArray(array,content);
  return array;
}
