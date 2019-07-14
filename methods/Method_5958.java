/** 
 * Reads up to the next NUL byte (or the limit) as UTF-8 characters.
 * @return The string not including any terminating NUL byte, or null if the end of the data hasalready been reached.
 */
public @Nullable String readNullTerminatedString(){
  if (bytesLeft() == 0) {
    return null;
  }
  int stringLimit=position;
  while (stringLimit < limit && data[stringLimit] != 0) {
    stringLimit++;
  }
  String string=Util.fromUtf8Bytes(data,position,stringLimit - position);
  position=stringLimit;
  if (position < limit) {
    position++;
  }
  return string;
}
