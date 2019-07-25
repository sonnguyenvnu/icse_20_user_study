@SuppressWarnings("NullAway") private void generate(OkBuckExtension okbuckExt,@Nullable String groovyHome,@Nullable String kotlinHome,@Nullable String scalaCompiler,@Nullable String scalaLibrary){
  try {
    dotBuckConfig().createNewFile();
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
  Map<String,RuleOverridesExtension.OverrideSetting> overrides=okbuckExt.getRuleOverridesExtension().getOverrides();
  new OkbuckTargets().resourceExcludes(okBuckExtension.excludeResources.stream().map(s -> "'" + s + "'").collect(Collectors.toSet())).classpathMacro(CLASSPATH_ABI_MACRO).lintJvmArgs(okbuckExt.getLintExtension().jvmArgs).enableLint(!okbuckExt.getLintExtension().disabled).jetifierConfigurationTarget(BuckRuleComposer.fileRule(okbuckExt.getJetifierExtension().customConfigFile)).externalDependencyCache(okbuckExt.getExternalDependenciesExtension().getCache()).classpathExclusionRegex(okbuckExt.getLintExtension().classpathExclusionRegex).useCompilationClasspath(okbuckExt.getLintExtension().useCompilationClasspath).render(okbuckTargets());
  Multimap<String,String> prebuiltLoadStatements=TreeMultimap.create();
  RuleOverridesExtension.OverrideSetting aarSetting=overrides.get(RuleType.ANDROID_PREBUILT_AAR.getBuckName());
  RuleOverridesExtension.OverrideSetting jarSetting=overrides.get(RuleType.PREBUILT_JAR.getBuckName());
  prebuiltLoadStatements.put(aarSetting.getImportLocation(),aarSetting.getNewRuleName());
  prebuiltLoadStatements.put(jarSetting.getImportLocation(),jarSetting.getNewRuleName());
  Rule okbuckPrebuiltRule=new OkbuckPrebuilt().prebuiltAarRule(aarSetting.getNewRuleName()).prebuiltJarRule(jarSetting.getNewRuleName());
  buckFileManager.writeToBuckFile(ImmutableList.of(okbuckPrebuiltRule),okbuckPrebuilt(),prebuiltLoadStatements);
  Multimap<String,String> unifiedLibsLoadStatements=TreeMultimap.create();
  RuleOverridesExtension.OverrideSetting androidResourceSetting=overrides.get(RuleType.ANDROID_RESOURCE.getBuckName());
  RuleOverridesExtension.OverrideSetting androidLibrarySetting=overrides.get(RuleType.ANDROID_LIBRARY.getBuckName());
  RuleOverridesExtension.OverrideSetting manifestSetting=overrides.get(RuleType.MANIFEST.getBuckName());
  if (androidResourceSetting != null) {
    unifiedLibsLoadStatements.put(androidResourceSetting.getImportLocation(),androidResourceSetting.getNewRuleName());
  }
  unifiedLibsLoadStatements.put(androidLibrarySetting.getImportLocation(),androidLibrarySetting.getNewRuleName());
  unifiedLibsLoadStatements.put(manifestSetting.getImportLocation(),manifestSetting.getNewRuleName());
  Rule okbuckAndroidModules=new OkbuckAndroidModules().androidLibraryRule(androidLibrarySetting.getNewRuleName()).manifestRule(manifestSetting.getNewRuleName()).androidResourceRule(androidResourceSetting != null ? androidResourceSetting.getNewRuleName() : "native." + RuleType.ANDROID_RESOURCE.getBuckName());
  buckFileManager.writeToBuckFile(ImmutableList.of(okbuckAndroidModules),okbuckAndroidModules(),unifiedLibsLoadStatements);
  OkbuckBuckConfigGenerator.generate(okbuckExt,groovyHome,kotlinHome,scalaCompiler,scalaLibrary,ProguardUtil.getProguardJarPath(getProject()),repositoryMap(okbuckExt.getExternalDependenciesExtension().shouldDownloadInBuck())).render(okbuckBuckConfig());
}
