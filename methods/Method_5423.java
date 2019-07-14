@C.SelectionFlags private static int parseSelectionFlags(String line){
  int flags=0;
  if (parseOptionalBooleanAttribute(line,REGEX_DEFAULT,false)) {
    flags|=C.SELECTION_FLAG_DEFAULT;
  }
  if (parseOptionalBooleanAttribute(line,REGEX_FORCED,false)) {
    flags|=C.SELECTION_FLAG_FORCED;
  }
  if (parseOptionalBooleanAttribute(line,REGEX_AUTOSELECT,false)) {
    flags|=C.SELECTION_FLAG_AUTOSELECT;
  }
  return flags;
}
