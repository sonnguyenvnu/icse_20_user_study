private static int getHexadecimalDigitValue(char c){
  if ((c >= 'a') && (c <= 'f')) {
    return (c - 'a') + 0x0a;
  }
 else   if ((c >= 'A') && (c <= 'F')) {
    return (c - 'A') + 0x0a;
  }
 else   if ((c >= '0') && (c <= '9')) {
    return c - '0';
  }
 else {
    throw new IllegalArgumentException("Invalid hexadecimal digit at position : '" + c + "' (0x" + Integer.toHexString(c) + ")");
  }
}
