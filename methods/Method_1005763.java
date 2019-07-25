public static Rule compose(AndroidTarget target,String jniLibDir){
  return new PrebuiltNativeLibraryRule().nativeLibs(jniLibDir).defaultVisibility().ruleType(RuleType.PREBUILT_NATIVE_LIBRARY.getBuckName()).name(prebuiltNative(target,jniLibDir));
}
