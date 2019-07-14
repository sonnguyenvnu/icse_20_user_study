private Pattern RETURN_PATH_PATTERN(){
  if (_RETURN_PATH_PATTERN == null) {
    buildPatterns();
  }
  return _RETURN_PATH_PATTERN;
}
