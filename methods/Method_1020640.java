/** 
 * ??18???????????????
 * @param idCardNumber 18???????
 * @return
 * @throws Exception
 */
public static boolean verify(String idCardNumber) throws Exception {
  if (idCardNumber == null || idCardNumber.length() != 18) {
    throw new Exception("??18???????");
  }
  return getVerifyCode(idCardNumber) == idCardNumber.charAt(idCardNumber.length() - 1);
}
