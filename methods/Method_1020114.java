/** 
 * ???????
 * @param user
 */
public void regist(User user){
  userRepository.save(user);
  try {
    MailUtils.sendMail(user.getEmail(),user.getCode());
  }
 catch (  Exception e) {
    System.out.println("??????" + e);
  }
}
