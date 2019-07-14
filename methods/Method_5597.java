private String[] parseStyleIds(String parentStyleIds){
  parentStyleIds=parentStyleIds.trim();
  return parentStyleIds.isEmpty() ? new String[0] : Util.split(parentStyleIds,"\\s+");
}
