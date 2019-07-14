private static boolean hasNewLine(@NonNull String source,int selectionStart){
  try {
    if (source.isEmpty())     return true;
    source=source.substring(0,selectionStart);
    return source.charAt(source.length() - 1) == 10;
  }
 catch (  StringIndexOutOfBoundsException e) {
    return false;
  }
}
