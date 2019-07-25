public static byte[] recombine(List<byte[]> encoded,int truncateTo,int originalBlobs,int allowedFailures){
  return recombine(new GaloisField256(),encoded.toArray(new byte[0][]),truncateTo,originalBlobs,allowedFailures);
}
