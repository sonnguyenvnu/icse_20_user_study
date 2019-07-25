private static boolean arraynequals(byte[] data,int index,byte[] expected,int length){
  if (data.length < index + length) {
    return false;
  }
  if (expected.length < length) {
    return false;
  }
  for (int i=0; i < length; i++) {
    if (data[index + i] != expected[i]) {
      return false;
    }
  }
  return true;
}
