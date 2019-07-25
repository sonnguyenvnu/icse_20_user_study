@NotNull private static String join(@NotNull String prefixPackage,@NotNull String suffixPackage){
  if (prefixPackage.isEmpty()) {
    return suffixPackage;
  }
  if (suffixPackage.isEmpty()) {
    return prefixPackage;
  }
  return prefixPackage + DOT + suffixPackage;
}
