private String identifier(String s){
  if (database.getMode().lowerCaseIdentifiers) {
    s=s == null ? null : StringUtils.toLowerEnglish(s);
  }
  return s;
}
