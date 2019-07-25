@Override public String format(String fieldText){
  AuthorList al=AuthorList.parse(fieldText);
  return al.getAsLastFirstFirstLastNamesWithAnd(true);
}
