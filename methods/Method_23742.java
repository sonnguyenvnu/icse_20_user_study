private void odsAppendNotNull(XML kid,StringBuilder buffer){
  String content=kid.getContent();
  if (content != null) {
    buffer.append(content);
  }
}
