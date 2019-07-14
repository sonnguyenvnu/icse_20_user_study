@Benchmark public char[] charArrayWriter(){
  final CharArrayWriter charArrayWriter=new CharArrayWriter();
  for (int i=0; i < size; i++) {
    charArrayWriter.append((char)i);
  }
  return charArrayWriter.toCharArray();
}
