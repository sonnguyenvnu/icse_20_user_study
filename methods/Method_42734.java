/** 
 */
@RequiresPermissions("pay:config:edit") @RequestMapping(value="/editBankUI",method=RequestMethod.GET) public String editBankUI(Model model,@RequestParam("userNo") String userNo){
  RpUserBankAccount rpUserBankAccount=rpUserBankAccountService.getByUserNo(userNo);
  RpUserInfo rpUserInfo=rpUserInfoService.getDataByMerchentNo(userNo);
  model.addAttribute("BankCodeEnums",BankCodeEnum.toList());
  model.addAttribute("BankAccountTypeEnums",BankAccountTypeEnum.toList());
  model.addAttribute("CardTypeEnums",CardTypeEnum.toList());
  model.addAttribute("rpUserBankAccount",rpUserBankAccount);
  model.addAttribute("rpUserInfo",rpUserInfo);
  return "pay/config/editBank";
}
