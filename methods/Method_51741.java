public static String getRuleSetFilename(String rulesetFileName){
  return FilenameUtils.getBaseName(StringUtils.chomp(rulesetFileName));
}
