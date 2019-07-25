private static ManifestRule compose(AndroidTarget target){
  return (ManifestRule)new ManifestRule().debuggable(target.getDebuggable()).minSdk(target.getMinSdk()).targetSdk(target.getTargetSdk()).versionCode(target.getVersionCode()).versionName(target.getVersionName()).mainManifest(target.getMainManifest()).secondaryManifests(target.getSecondaryManifests()).ruleType(RuleType.MANIFEST.getBuckName());
}
