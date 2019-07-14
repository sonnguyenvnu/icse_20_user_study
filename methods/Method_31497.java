@Override protected String computeVersionDisplayName(MigrationVersion version){
  if (getVersion().isAtLeast("8")) {
    if ("8".equals(getVersion().getMajorAsString())) {
      return "2000";
    }
    if ("9".equals(getVersion().getMajorAsString())) {
      return "2005";
    }
    if ("10".equals(getVersion().getMajorAsString())) {
      if ("0".equals(getVersion().getMinorAsString())) {
        return "2008";
      }
      return "2008 R2";
    }
    if ("11".equals(getVersion().getMajorAsString())) {
      return "2012";
    }
    if ("12".equals(getVersion().getMajorAsString())) {
      return "2014";
    }
    if ("13".equals(getVersion().getMajorAsString())) {
      return "2016";
    }
    if ("14".equals(getVersion().getMajorAsString())) {
      return "2017";
    }
    if ("15".equals(getVersion().getMajorAsString())) {
      return "2019";
    }
  }
  return super.computeVersionDisplayName(version);
}
