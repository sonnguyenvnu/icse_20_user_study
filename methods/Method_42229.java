/** 
 * verrevcmp helper function 
 */
private static int order(int c){
  if (c_isdigit(c))   return 0;
 else   if (c_isalpha(c))   return c;
 else   if (c == '~')   return -1;
 else   return (int)c + UNICODE_MAX + 1;
}
