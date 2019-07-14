static long hostToBundleId(String host){
  Matcher m=FELIX_BUNDLE_ID_PATTERN.matcher(host);
  if (m.find()) {
    return Double.valueOf(m.group(1)).longValue();
  }
 else {
    m=EQUINOX_BUNDLE_ID_PATTERN.matcher(host);
    if (m.find()) {
      return Double.valueOf(m.group()).longValue();
    }
  }
  throw new FlywayException("There's no bundleId in passed URL");
}
