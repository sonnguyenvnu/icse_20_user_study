/** 
 * ???????
 * @return
 */
public static String getnonceStr(){
  Random random=new Random();
  StringBuilder nonceStrBuilder=new StringBuilder();
  for (int i=0; i < 31; i++) {
    nonceStrBuilder.append(random.nextInt(10));
  }
  return nonceStrBuilder.toString();
}
