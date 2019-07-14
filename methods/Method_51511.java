private static String keyFor(RuleViolation rv){
  return StringUtils.isNotBlank(rv.getPackageName()) ? rv.getPackageName() + '.' + rv.getClassName() : "";
}
