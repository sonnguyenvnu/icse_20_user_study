private Pattern MAILBOX_PATTERN(){
  if (_MAILBOX_PATTERN == null) {
    buildPatterns();
  }
  return _MAILBOX_PATTERN;
}
