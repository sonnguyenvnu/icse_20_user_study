/** 
 * ????
 * @param startDate
 * @param appDetailVO
 * @param appDailyData
 */
public void noticeAppDaily(Date startDate,AppDetailVO appDetailVO,AppDailyData appDailyData){
  List<String> ccEmailList=getCCEmailList(appDetailVO.getAppDesc());
  String startDateFormat=DateUtil.formatYYYYMMdd(startDate);
  String title=String.format("?CacheCloud?%s??(appId=%s)",startDateFormat,appDetailVO.getAppDesc().getAppId());
  String mailContent=VelocityUtils.createText(velocityEngine,appDetailVO.getAppDesc(),null,appDailyData,null,"appDaily.vm","UTF-8");
  emailComponent.sendMail(title,mailContent,appDetailVO.getEmailList(),ccEmailList);
}
