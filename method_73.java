private Calendar _XXXXX_(String version){
  Matcher m=VersionUtil.UNIQUE_SNAPSHOT_PATTERN.matcher(version);
  if (m.matches()) {
    Matcher mtimestamp=VersionUtil.TIMESTAMP_PATTERN.matcher(m.group(2));
    if (mtimestamp.matches()) {
      String tsDate=mtimestamp.group(1);
      String tsTime=mtimestamp.group(2);
      Date versionDate;
      try {
        versionDate=timestampParser.parse(tsDate + "." + tsTime);
        Calendar cal=Calendar.getInstance(DateUtils.UTC_TIME_ZONE);
        cal.setTime(versionDate);
        return cal;
      }
 catch (      ParseException e) {
        return null;
      }
    }
  }
  return null;
}