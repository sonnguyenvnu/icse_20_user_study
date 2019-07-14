/** 
 * ?????
 * @return
 */
public final static String createNonceStr(){
  StringBuilder nonceStr=new StringBuilder();
  Random random=new Random();
  for (int i=0, lenght=31; i < lenght; i++) {
    nonceStr.append(random.nextInt(10));
  }
  logger.info("??????????:[{}]",nonceStr);
  return nonceStr.toString();
}
