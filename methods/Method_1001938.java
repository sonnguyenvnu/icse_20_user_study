private static byte[] decode(String key) throws IOException {
  final BigInteger lu=new BigInteger(key,36);
  final QBlock qb2=new QBlock(lu);
  final QBlock qb3=qb2.change(Dedication.E,Dedication.N);
  byte block[]=qb3.getData512();
  if (block.length != 512) {
    throw new IOException();
  }
  return block;
}
