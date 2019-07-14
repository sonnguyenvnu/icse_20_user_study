/** 
 * ????
 * @return
 */
@RequestMapping("/checkUsername") @ResponseBody public ResultGeekQ<Boolean> checkUsername(String username){
  ResultGeekQ<Boolean> result=ResultGeekQ.build();
  boolean nickNameCount=miaoShaUserService.getNickNameCount(username);
  result.setData(nickNameCount);
  return result;
}
