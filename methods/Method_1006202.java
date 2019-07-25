@Override public String format(String fieldText){
  StringBuilder sb=new StringBuilder(100);
  AuthorList al=AuthorList.parse(fieldText);
  DocBookAuthorFormatter formatter=new DocBookAuthorFormatter();
  formatter.addBody(sb,al,FieldName.EDITOR,DocBookVersion.DOCBOOK_4);
  return sb.toString();
}
