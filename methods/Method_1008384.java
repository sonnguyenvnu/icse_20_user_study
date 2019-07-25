/** 
 * Validates that the setting is valid
 */
void validate(String key,Settings settings,boolean validateDependencies){
  Setting setting=getRaw(key);
  if (setting == null) {
    LevensteinDistance ld=new LevensteinDistance();
    List<Tuple<Float,String>> scoredKeys=new ArrayList<>();
    for (    String k : this.keySettings.keySet()) {
      float distance=ld.getDistance(key,k);
      if (distance > 0.7f) {
        scoredKeys.add(new Tuple<>(distance,k));
      }
    }
    CollectionUtil.timSort(scoredKeys,(a,b) -> b.v1().compareTo(a.v1()));
    String msgPrefix="unknown setting";
    SecureSettings secureSettings=settings.getSecureSettings();
    if (secureSettings != null && settings.getSecureSettings().getSettingNames().contains(key)) {
      msgPrefix="unknown secure setting";
    }
    String msg=msgPrefix + " [" + key + "]";
    List<String> keys=scoredKeys.stream().map((a) -> a.v2()).collect(Collectors.toList());
    if (keys.isEmpty() == false) {
      msg+=" did you mean " + (keys.size() == 1 ? "[" + keys.get(0) + "]" : "any of " + keys.toString()) + "?";
    }
 else {
      msg+=" please check that any required plugins are installed, or check the breaking changes documentation for removed " + "settings";
    }
    throw new IllegalArgumentException(msg);
  }
 else {
    Set<String> settingsDependencies=setting.getSettingsDependencies(key);
    if (setting.hasComplexMatcher()) {
      setting=setting.getConcreteSetting(key);
    }
    if (validateDependencies && settingsDependencies.isEmpty() == false) {
      Set<String> settingKeys=settings.keySet();
      for (      String requiredSetting : settingsDependencies) {
        if (settingKeys.contains(requiredSetting) == false) {
          throw new IllegalArgumentException("Missing required setting [" + requiredSetting + "] for setting [" + setting.getKey() + "]");
        }
      }
    }
  }
  setting.get(settings);
}
