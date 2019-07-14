/** 
 * ??Pms????.
 * @param pmsPermission .
 * @return msg .
 */
private String validatePmsPermission(PmsPermission pmsPermission){
  String msg="";
  String permissionName=pmsPermission.getPermissionName();
  String permission=pmsPermission.getPermission();
  String desc=pmsPermission.getRemark();
  msg+=ValidateUtils.lengthValidate("????",permissionName,true,3,90);
  msg+=ValidateUtils.lengthValidate("????",permission,true,3,100);
  msg+=ValidateUtils.lengthValidate("??",desc,true,3,60);
  return msg;
}
