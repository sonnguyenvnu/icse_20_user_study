static ThreePartName parse(String theProcName){
  String procedurePart=null;
  String ownerPart=null;
  String databasePart=null;
  Matcher matcher;
  if (null != theProcName) {
    matcher=THREE_PART_NAME.matcher(theProcName);
    if (matcher.matches()) {
      if (matcher.group(2) != null) {
        databasePart=matcher.group(1);
        matcher=THREE_PART_NAME.matcher(matcher.group(2));
        if (matcher.matches()) {
          if (null != matcher.group(2)) {
            ownerPart=matcher.group(1);
            procedurePart=matcher.group(2);
          }
 else {
            ownerPart=databasePart;
            databasePart=null;
            procedurePart=matcher.group(1);
          }
        }
      }
 else {
        procedurePart=matcher.group(1);
      }
    }
 else {
      procedurePart=theProcName;
    }
  }
  return new ThreePartName(databasePart,ownerPart,procedurePart);
}
