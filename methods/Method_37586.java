/** 
 * Compares the content of two character streams ( {@link Reader}s).
 * @return {@code true} if the content of the first {@link Reader} is equalto the content of the second  {@link Reader}.
 */
public static boolean compare(Reader input1,Reader input2) throws IOException {
  if (!(input1 instanceof BufferedReader)) {
    input1=new BufferedReader(input1);
  }
  if (!(input2 instanceof BufferedReader)) {
    input2=new BufferedReader(input2);
  }
  int ch=input1.read();
  while (ch != NEGATIVE_ONE) {
    int ch2=input2.read();
    if (ch != ch2) {
      return false;
    }
    ch=input1.read();
  }
  int ch2=input2.read();
  return (ch2 == NEGATIVE_ONE);
}
