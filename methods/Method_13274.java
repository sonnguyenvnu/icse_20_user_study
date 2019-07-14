public void find(String text){
  sb.setLength(0);
  try {
    XMLInputFactory factory=XMLInputFactory.newInstance();
    XMLStreamReader reader=factory.createXMLStreamReader(new StringReader(text));
    String tagName="";
    int offset=0;
    while (reader.hasNext()) {
      reader.next();
switch (reader.getEventType()) {
case XMLStreamReader.START_ELEMENT:
        sb.append('/').append(tagName=reader.getLocalName());
      offset=reader.getLocation().getCharacterOffset();
    break;
case XMLStreamReader.END_ELEMENT:
  sb.setLength(sb.length() - reader.getLocalName().length() - 1);
break;
case XMLStreamReader.CHARACTERS:
HashSet<String> setOfPaths=tagNameToPaths.get(tagName);
if (setOfPaths != null) {
String path=sb.toString();
if (setOfPaths.contains(path)) {
while (offset > 0) {
  if (text.charAt(offset) == '>') {
    break;
  }
 else {
    offset--;
  }
}
handle(path.substring(1),reader.getText(),offset + 1);
}
}
break;
}
}
}
 catch (XMLStreamException e) {
assert ExceptionUtil.printStackTrace(e);
}
}
