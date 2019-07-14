void writeStartDocument(){
  writer.println(XML_HEADER);
  writer.println(PLIST_DTD);
  writeStartElement(PLIST_TAG,PLIST_VERSION_ATTRIBUTE,"1.0");
}
