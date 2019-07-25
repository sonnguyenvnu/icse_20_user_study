public QBlocks change(BigInteger E,BigInteger N){
  final QBlocks result=new QBlocks();
  for (  QBlock rsa : all) {
    result.all.add(rsa.change(E,N));
  }
  return result;
}
