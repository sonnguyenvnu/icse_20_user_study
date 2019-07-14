/** 
 * Unescapes an escaped file or directory name back to its original value. <p>See  {@link #escapeFileName(String)} for more information.
 * @param fileName File name to be unescaped.
 * @return The original value of the file name before it was escaped, or null if the escapedfileName seems invalid.
 */
public static @Nullable String unescapeFileName(String fileName){
  int length=fileName.length();
  int percentCharacterCount=0;
  for (int i=0; i < length; i++) {
    if (fileName.charAt(i) == '%') {
      percentCharacterCount++;
    }
  }
  if (percentCharacterCount == 0) {
    return fileName;
  }
  int expectedLength=length - percentCharacterCount * 2;
  StringBuilder builder=new StringBuilder(expectedLength);
  Matcher matcher=ESCAPED_CHARACTER_PATTERN.matcher(fileName);
  int startOfNotEscaped=0;
  while (percentCharacterCount > 0 && matcher.find()) {
    char unescapedCharacter=(char)Integer.parseInt(matcher.group(1),16);
    builder.append(fileName,startOfNotEscaped,matcher.start()).append(unescapedCharacter);
    startOfNotEscaped=matcher.end();
    percentCharacterCount--;
  }
  if (startOfNotEscaped < length) {
    builder.append(fileName,startOfNotEscaped,length);
  }
  if (builder.length() != expectedLength) {
    return null;
  }
  return builder.toString();
}
