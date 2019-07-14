public boolean isxLock(){
  return StringUtils.endsWithIgnoreCase(StringUtils.trimAllWhitespace(this.select.toString()),StringUtils.trimAllWhitespace(SqlUtils.FOR_UPDATE));
}
