static void skipStyleBlock(ParsableByteArray input){
  String line;
  do {
    line=input.readLine();
  }
 while (!TextUtils.isEmpty(line));
}
