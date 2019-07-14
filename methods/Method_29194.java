/** 
 * ??????
 * @param info
 */
private void sendEmailAlert(InstanceInfo info){
  if (info == null) {
    return;
  }
  String title="??(" + info.getIp() + ":" + info.getPort() + ")??????";
  String message=generateMessage(info,true);
  emailComponent.sendMailToAdmin(title,message);
}
