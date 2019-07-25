public static IncludeExclude merge(IncludeExclude include,IncludeExclude exclude){
  if (include == null) {
    return exclude;
  }
  if (exclude == null) {
    return include;
  }
  if (include.isPartitionBased()) {
    throw new IllegalArgumentException("Cannot specify any excludes when using a partition-based include");
  }
  String includeMethod=include.isRegexBased() ? "regex" : "set";
  String excludeMethod=exclude.isRegexBased() ? "regex" : "set";
  if (includeMethod.equals(excludeMethod) == false) {
    throw new IllegalArgumentException("Cannot mix a " + includeMethod + "-based include with a " + excludeMethod + "-based method");
  }
  if (include.isRegexBased()) {
    return new IncludeExclude(include.include,exclude.exclude);
  }
 else {
    return new IncludeExclude(include.includeValues,exclude.excludeValues);
  }
}
