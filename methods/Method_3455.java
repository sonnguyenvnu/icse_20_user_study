@Override public boolean load(ByteArray byteArray){
  l1=byteArray.nextDouble();
  l2=byteArray.nextDouble();
  l3=byteArray.nextDouble();
  tf.load(byteArray);
  return true;
}
