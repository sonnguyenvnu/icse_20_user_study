public boolean hasValueMatching(final StringValuePattern valuePattern){
  return (valuePattern.nullSafeIsAbsent() && !isPresent()) || anyValueMatches(valuePattern);
}
