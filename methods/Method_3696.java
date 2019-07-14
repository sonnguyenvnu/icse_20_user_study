/** 
 * ??long??char??
 * @param x
 */
public static char[] long2char(long x){
  char[] c=new char[4];
  c[0]=(char)(x >> 48);
  c[1]=(char)(x >> 32);
  c[2]=(char)(x >> 16);
  c[3]=(char)(x);
  return c;
}
