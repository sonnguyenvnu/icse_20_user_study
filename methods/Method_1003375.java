/** 
 * Get or create a UUID for the given text representation.
 * @param s the text representation of the UUID
 * @return the UUID
 */
public static ValueUuid get(String s){
  long low=0, high=0;
  for (int i=0, j=0, length=s.length(); i < length; i++) {
    char c=s.charAt(i);
    if (c >= '0' && c <= '9') {
      low=(low << 4) | (c - '0');
    }
 else     if (c >= 'a' && c <= 'f') {
      low=(low << 4) | (c - 'a' + 0xa);
    }
 else     if (c == '-') {
      continue;
    }
 else     if (c >= 'A' && c <= 'F') {
      low=(low << 4) | (c - 'A' + 0xa);
    }
 else     if (c <= ' ') {
      continue;
    }
 else {
      throw DbException.get(ErrorCode.DATA_CONVERSION_ERROR_1,s);
    }
    if (j++ == 15) {
      high=low;
      low=0;
    }
  }
  return (ValueUuid)Value.cache(new ValueUuid(high,low));
}
