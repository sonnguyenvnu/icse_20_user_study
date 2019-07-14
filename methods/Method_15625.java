public static String concat(String left,String right,String split,boolean trim){
  if (isEmpty(left,trim)) {
    return right;
  }
  if (isEmpty(right,trim)) {
    return left;
  }
  if (split == null) {
    split=",";
  }
  return left + split + right;
}
