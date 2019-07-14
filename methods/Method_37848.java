/** 
 * Converts hex char to int value.
 */
public static int hex2int(final char c){
switch (c) {
case '0':
case '1':
case '2':
case '3':
case '4':
case '5':
case '6':
case '7':
case '8':
case '9':
    return c - '0';
case 'A':
case 'B':
case 'C':
case 'D':
case 'E':
case 'F':
  return c - 55;
case 'a':
case 'b':
case 'c':
case 'd':
case 'e':
case 'f':
return c - 87;
default :
throw new IllegalArgumentException("Not a hex: " + c);
}
}
