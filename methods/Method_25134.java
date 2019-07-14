/** 
 * Default to compiling with the same -source and -target as the host's javac. <p>This prevents, e.g., targeting Java 8 by default when using error-prone on JDK7.
 */
private static ImmutableList<String> defaultToLatestSupportedLanguageLevel(ImmutableList<String> args){
  String overrideLanguageLevel;
switch (JAVA_SPECIFICATION_VERSION.value()) {
case "1.7":
    overrideLanguageLevel="7";
  break;
case "1.8":
overrideLanguageLevel="8";
break;
default :
return args;
}
return ImmutableList.<String>builder().add("-Xlint:-options","-source",overrideLanguageLevel,"-target",overrideLanguageLevel).addAll(args).build();
}
