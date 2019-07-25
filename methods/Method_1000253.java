/** 
 * Calculate sum
 * @param a length of a should always be greater or equal to b
 * @param b length of b should always be smaller of equal to a
 * @return
 */
private String calculate(String a,String b){
  int carry=0;
  int d=a.length() - b.length();
  StringBuilder sb=new StringBuilder();
  for (int i=a.length() - 1; i >= 0; i--) {
    int first=Integer.parseInt(String.valueOf(a.charAt(i)));
    int second=i - d >= 0 ? Integer.parseInt(String.valueOf(b.charAt(i - d))) : 0;
    int sum=(first + second + carry);
    carry=sum / 2;
    sb.append(sum % 2);
  }
  if (carry != 0)   sb.append(carry);
  return sb.reverse().toString();
}
