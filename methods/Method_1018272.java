private String normalize(String file){
  int afterLastSeparatorIndex=file.lastIndexOf(SEPARATOR) + SEPARATOR.length();
  String afterSeparator=file.substring(afterLastSeparatorIndex);
  afterSeparator=replaceParentDir(afterSeparator);
  afterSeparator=replaceCurrentDir(afterSeparator);
  return new StringBuilder(afterLastSeparatorIndex + afterSeparator.length()).append(file.substring(0,afterLastSeparatorIndex)).append(afterSeparator).toString();
}
