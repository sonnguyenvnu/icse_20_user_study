/** 
 * Run the tests.
 * @param args the command line parameters
 */
public static void main(String... args){
  check(new String(encode(new byte[]{})),"");
  check(new String(encode("A".getBytes())),"QQ==");
  check(new String(encode("AB".getBytes())),"QUI=");
  check(new String(encode("ABC".getBytes())),"QUJD");
  check(new String(encode("ABCD".getBytes())),"QUJDRA==");
  check(new String(decode(new byte[]{})),"");
  check(new String(decode("QQ==".getBytes())),"A");
  check(new String(decode("QUI=".getBytes())),"AB");
  check(new String(decode("QUJD".getBytes())),"ABC");
  check(new String(decode("QUJDRA==".getBytes())),"ABCD");
  int len=10000;
  test(false,len);
  test(true,len);
  test(false,len);
  test(true,len);
}
