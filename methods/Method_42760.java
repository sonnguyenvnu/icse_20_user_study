/** 
 * ????????.
 * @param pmsRole ????.
 * @return msg .
 */
private String validatePmsRole(PmsRole pmsRole){
  String msg="";
  String roleName=pmsRole.getRoleName();
  String desc=pmsRole.getRemark();
  msg+=ValidateUtils.lengthValidate("????",roleName,true,3,90);
  msg+=ValidateUtils.lengthValidate("??",desc,true,3,300);
  return msg;
}
