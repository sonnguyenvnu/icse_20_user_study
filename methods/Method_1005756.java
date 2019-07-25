public static Rule compose(List<String> deps,AndroidAppTarget mainApkTarget,@Nullable String manifestRule){
  return new InstrumentationApkRule().manifest(manifestRule).mainApkRuleName(bin(mainApkTarget)).defaultVisibility().ruleType(RuleType.ANDROID_INSTRUMENTATION_APK.getBuckName()).name(instrumentation(mainApkTarget)).deps(deps);
}
