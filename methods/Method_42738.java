/** 
 */
@RequestMapping(value="/getSettAmount",method=RequestMethod.GET) @ResponseBody public RpAccount getSettAmount(@RequestParam("userNo") String userNo){
  RpAccount rpAccount=rpAccountService.getDataByUserNo(userNo);
  return rpAccount;
}
