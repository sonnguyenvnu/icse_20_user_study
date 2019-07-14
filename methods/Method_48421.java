protected int hashCode(int length){
  Preconditions.checkArgument(length <= length());
  int result=17;
  for (int i=offset; i < offset + length; i++) {
    result=31 * result + (int)array[i];
  }
  return result;
}
