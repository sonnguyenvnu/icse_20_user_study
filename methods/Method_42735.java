/** 
 */
@RequiresPermissions("pay:config:edit") @RequestMapping(value="/editBank",method=RequestMethod.POST) public String editBank(Model model,RpUserBankAccount rpUserBankAccount,DwzAjax dwz){
  rpUserBankAccountService.createOrUpdate(rpUserBankAccount);
  dwz.setStatusCode(DWZ.SUCCESS);
  dwz.setMessage(DWZ.SUCCESS_MSG);
  model.addAttribute("dwz",dwz);
  return DWZ.AJAX_DONE;
}
