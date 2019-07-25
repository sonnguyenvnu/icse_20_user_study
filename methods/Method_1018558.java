private static byte[] hash(byte[] input,int iterations){
  for (int i=0; i < iterations; i++)   input=Blake2b.Digest.newInstance(64).digest(input);
  return Arrays.copyOfRange(input,0,32);
}
