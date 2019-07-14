/** 
 * ?????????????
 * @param loginPwd
 * @return ????
 */
public static boolean loginPwdFormat(String loginPwd){
  return loginPwd.matches(".*?[^a-zA-Z\\d]+.*?") && loginPwd.matches(".*?[a-zA-Z]+.*?") && loginPwd.matches(".*?[\\d]+.*?");
}
