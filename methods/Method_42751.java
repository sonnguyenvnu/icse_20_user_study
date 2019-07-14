/** 
 * ??Pms???????.
 * @param PmsOperator ?????.
 * @param roleOperatorStr ?????ID?.
 * @return
 */
private String validatePmsOperator(PmsOperator operator,String roleOperatorStr){
  String msg="";
  msg+=ValidateUtils.lengthValidate("????",operator.getRealName(),true,2,15);
  msg+=ValidateUtils.lengthValidate("???",operator.getLoginName(),true,3,50);
  String mobileNo=operator.getMobileNo();
  String mobileNoMsg=ValidateUtils.lengthValidate("???",mobileNo,true,0,12);
  if (StringUtils.isBlank(mobileNoMsg) && !ValidateUtils.isMobile(mobileNo)) {
    mobileNoMsg+="?????????";
  }
  msg+=mobileNoMsg;
  String status=operator.getStatus();
  if (status == null) {
    msg+="??????";
  }
 else   if (!PublicStatusEnum.ACTIVE.name().equals(status) || PublicStatusEnum.UNACTIVE.name().equals(status)) {
    msg+="???????";
  }
  msg+=ValidateUtils.lengthValidate("??",operator.getRemark(),true,3,100);
  if (StringUtils.isBlank(roleOperatorStr) && operator.getId() == null) {
    msg+="????????????";
  }
  return msg;
}
