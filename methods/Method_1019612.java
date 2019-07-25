/** 
 * Returns the token type associated with the given text, if the given text is in this token map.  If it isn't, <code>-1</code> is returned.
 * @param array1 An array of characters containing the text.
 * @param start The starting index in the array of the text.
 * @param end The ending index in the array of the text.
 * @return The token type associated with the given text, or<code>-1</code> if this token was not specified in this map.
 */
public int get(char[] array1,int start,int end){
  int length1=end - start + 1;
  int hash=getHashCode(array1,start,length1);
  TokenMapToken token=tokenMap[hash];
  char[] array2;
  int offset2;
  int offset1;
  int length;
  if (!ignoreCase) {
    mainLoop:     while (token != null) {
      if (token.length == length1) {
        array2=token.text;
        offset2=token.offset;
        offset1=start;
        length=length1;
        while (length-- > 0) {
          if (array1[offset1++] != array2[offset2++]) {
            token=token.nextToken;
            continue mainLoop;
          }
        }
        return token.tokenType;
      }
      token=token.nextToken;
    }
  }
 else {
    mainLoop2:     while (token != null) {
      if (token.length == length1) {
        array2=token.text;
        offset2=token.offset;
        offset1=start;
        length=length1;
        while (length-- > 0) {
          if (RSyntaxUtilities.toLowerCase(array1[offset1++]) != array2[offset2++]) {
            token=token.nextToken;
            continue mainLoop2;
          }
        }
        return token.tokenType;
      }
      token=token.nextToken;
    }
  }
  return -1;
}
