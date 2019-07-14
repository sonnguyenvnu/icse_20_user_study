@Override protected Collection<String> defaultSuppressionAnnotations(){
  Collection<String> ignoredStrings=new ArrayList<>();
  ignoredStrings.add("com.google.common.annotations.VisibleForTesting");
  ignoredStrings.add("android.support.annotation.VisibleForTesting");
  return ignoredStrings;
}
