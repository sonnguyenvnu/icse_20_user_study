/** 
 * Internal method to perform the normalization.
 * @param filename file name
 * @param separator separator character to use
 * @param keepSeparator <code>true</code> to keep the final separator
 * @return normalized filename
 */
private static String doNormalize(final String filename,final char separator,final boolean keepSeparator){
  if (filename == null) {
    return null;
  }
  int size=filename.length();
  if (size == 0) {
    return filename;
  }
  int prefix=getPrefixLength(filename);
  if (prefix < 0) {
    return null;
  }
  char[] array=new char[size + 2];
  filename.getChars(0,filename.length(),array,0);
  char otherSeparator=(separator == SYSTEM_SEPARATOR ? OTHER_SEPARATOR : SYSTEM_SEPARATOR);
  for (int i=0; i < array.length; i++) {
    if (array[i] == otherSeparator) {
      array[i]=separator;
    }
  }
  boolean lastIsDirectory=true;
  if (array[size - 1] != separator) {
    array[size++]=separator;
    lastIsDirectory=false;
  }
  for (int i=prefix + 1; i < size; i++) {
    if (array[i] == separator && array[i - 1] == separator) {
      System.arraycopy(array,i,array,i - 1,size - i);
      size--;
      i--;
    }
  }
  for (int i=prefix + 1; i < size; i++) {
    if (array[i] == separator && array[i - 1] == '.' && (i == prefix + 1 || array[i - 2] == separator)) {
      if (i == size - 1) {
        lastIsDirectory=true;
      }
      System.arraycopy(array,i + 1,array,i - 1,size - i);
      size-=2;
      i--;
    }
  }
  outer:   for (int i=prefix + 2; i < size; i++) {
    if (array[i] == separator && array[i - 1] == '.' && array[i - 2] == '.' && (i == prefix + 2 || array[i - 3] == separator)) {
      if (i == prefix + 2) {
        return null;
      }
      if (i == size - 1) {
        lastIsDirectory=true;
      }
      int j;
      for (j=i - 4; j >= prefix; j--) {
        if (array[j] == separator) {
          System.arraycopy(array,i + 1,array,j + 1,size - i);
          size-=(i - j);
          i=j + 1;
          continue outer;
        }
      }
      System.arraycopy(array,i + 1,array,prefix,size - i);
      size-=(i + 1 - prefix);
      i=prefix + 1;
    }
  }
  if (size <= 0) {
    return StringPool.EMPTY;
  }
  if (size <= prefix) {
    return new String(array,0,size);
  }
  if (lastIsDirectory && keepSeparator) {
    return new String(array,0,size);
  }
  return new String(array,0,size - 1);
}
