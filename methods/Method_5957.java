/** 
 * Reads the next  {@code length} bytes as UTF-8 characters. A terminating NUL byte is discarded,if present.
 * @param length The number of bytes to read.
 * @return The string, not including any terminating NUL byte.
 */
public String readNullTerminatedString(int length){
  if (length == 0) {
    return "";
  }
  int stringLength=length;
  int lastIndex=position + length - 1;
  if (lastIndex < limit && data[lastIndex] == 0) {
    stringLength--;
  }
  String result=Util.fromUtf8Bytes(data,position,stringLength);
  position+=length;
  return result;
}
