@Override public boolean isSupportedVersion(String version){
  return XPATH_1_0_COMPATIBILITY.equals(version) || XPATH_2_0.equals(version);
}
