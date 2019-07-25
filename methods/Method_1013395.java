public static String print(BigInteger[] A){
  StringBuffer sb=new StringBuffer();
  for (int i=0; i < A.length; i++) {
    sb.append(A[i].toString());
    sb.append(", ");
  }
  return new String(sb);
}
