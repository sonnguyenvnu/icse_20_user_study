public static Rule compose(AndroidTarget target){
  return new BuildConfigRule().pkg(target.getPackage()).values(target.getBuildConfigFields()).defaultVisibility().ruleType(RuleType.ANDROID_BUILD_CONFIG.getBuckName()).name(AndroidBuckRuleComposer.buildConfig(target));
}
