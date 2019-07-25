public static Rule compose(AndroidAppTarget target){
  List<String> deps=new ArrayList<>();
  ExoPackageScope exopackage=target.getExopackage();
  Set<String> extraBuckOpts=new HashSet<>(target.getExtraOpts(RuleType.ANDROID_LIBRARY));
  if (exopackage != null) {
    deps.addAll(external(exopackage.getExternalDeps()));
    deps.addAll(targets(exopackage.getTargetDeps()));
    extraBuckOpts.add("srcs = ['" + exopackage.getAppClass() + "']");
  }
  deps.add(":" + buildConfig(target));
  Set<String> libraryAptDeps=new LinkedHashSet<>();
  libraryAptDeps.addAll(externalApt(target.getExternalAptDeps(false)));
  libraryAptDeps.addAll(targetsApt(target.getTargetAptDeps(false)));
  Set<String> providedDeps=new LinkedHashSet<>();
  providedDeps.add(D8Util.RT_STUB_JAR_RULE);
  AndroidRule androidRule=new AndroidRule().sourceCompatibility(target.getSourceCompatibility()).targetCompatibility(target.getTargetCompatibility()).apPlugins(getApPlugins(target.getApPlugins())).aptDeps(libraryAptDeps).providedDeps(providedDeps).disableLint(true).options(target.getMain().getCustomOptions());
  @Var String ruleType=RuleType.ANDROID_LIBRARY.getBuckName();
  if (target.isKotlin()) {
    androidRule.language("kotlin");
    ruleType=RuleType.KOTLIN_ANDROID_LIBRARY.getBuckName();
  }
  return androidRule.ruleType(ruleType).defaultVisibility().deps(deps).name(appLib(target)).extraBuckOpts(extraBuckOpts);
}
