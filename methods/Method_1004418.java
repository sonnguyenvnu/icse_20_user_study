public static boolean contains(List<byte[]> source,byte[] target){
  for (  byte[] bytes : source) {
    if (Arrays.equals(bytes,target)) {
      return true;
    }
  }
  return false;
}
