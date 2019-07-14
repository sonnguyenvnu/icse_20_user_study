protected String fixEntryName(String entryName){
  if (entryName.indexOf('\\') >= 0) {
    return fixEntryName(entryName.replaceAll("\\\\",SEPARATOR));
  }
 else   if (entryName.indexOf(SEPARATOR_CHAR) == 0) {
    return entryName.replaceAll("^/+","");
  }
 else {
    return entryName;
  }
}
