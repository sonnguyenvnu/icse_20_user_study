@Override public String format(String fieldText){
  StringBuilder sb=new StringBuilder(100);
  AuthorList al=AuthorList.parse(fieldText);
  DocBookAuthorFormatter authorFormatter=new DocBookAuthorFormatter();
  authorFormatter.addBody(sb,al,FieldName.AUTHOR,DocBookVersion.DOCBOOK_5);
  return sb.toString();
}
