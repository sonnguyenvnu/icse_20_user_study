public boolean issLock(){
  return StringUtils.endsWithIgnoreCase(StringUtils.trimAllWhitespace(this.select.toString()),StringUtils.trimAllWhitespace(SqlUtils.LOCK_IN_SHARE_MODE));
}
