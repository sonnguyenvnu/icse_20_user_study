/** 
 * Collects 1A0001.
 * @param userId the specified user id
 * @return result
 */
public synchronized JSONObject collect1A0001(final String userId){
  final JSONObject ret=new JSONObject().put(Keys.STATUS_CODE,false);
  if (!activityQueryService.is1A0001Today(userId)) {
    ret.put(Keys.MSG,langPropsService.get("activityNotParticipatedLabel"));
    return ret;
  }
  if (activityQueryService.isCollected1A0001Today(userId)) {
    ret.put(Keys.MSG,langPropsService.get("activityParticipatedLabel"));
    return ret;
  }
  final List<JSONObject> records=pointtransferQueryService.getLatestPointtransfers(userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_1A0001,1);
  final JSONObject pointtransfer=records.get(0);
  final String data=pointtransfer.optString(Pointtransfer.DATA_ID);
  final String smallOrLarge=data.split("-")[1];
  final int sum=pointtransfer.optInt(Pointtransfer.SUM);
  String smallOrLargeResult=null;
  try {
    final Document doc=Jsoup.parse(new URL("http://stockpage.10jqka.com.cn/1A0001/quote/header/"),5000);
    final JSONObject result=new JSONObject(doc.text());
    final String price=result.optJSONObject("data").optJSONObject("1A0001").optString("10");
    if (!price.contains(".")) {
      smallOrLargeResult="0";
    }
 else {
      int endInt=0;
      if (price.split("\\.")[1].length() > 1) {
        final String end=price.substring(price.length() - 1);
        endInt=Integer.valueOf(end);
      }
      if (0 <= endInt && endInt <= 4) {
        smallOrLargeResult="0";
      }
 else       if (5 <= endInt && endInt <= 9) {
        smallOrLargeResult="1";
      }
 else {
        LOGGER.error("Activity 1A0001 collect result [" + endInt + "]");
      }
    }
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Collect 1A0001 failed",e);
    ret.put(Keys.MSG,langPropsService.get("activity1A0001CollectFailLabel"));
    return ret;
  }
  if (StringUtils.isBlank(smallOrLarge)) {
    ret.put(Keys.MSG,langPropsService.get("activity1A0001CollectFailLabel"));
    return ret;
  }
  ret.put(Keys.STATUS_CODE,true);
  if (StringUtils.equals(smallOrLarge,smallOrLargeResult)) {
    final int amount=sum * 2;
    final boolean succ=null != pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_1A0001_COLLECT,amount,DateFormatUtils.format(new Date(),"yyyyMMdd") + "-" + smallOrLargeResult,System.currentTimeMillis(),"");
    if (succ) {
      String msg=langPropsService.get("activity1A0001CollectSucc1Label");
      msg=msg.replace("{point}",String.valueOf(amount));
      ret.put(Keys.MSG,msg);
    }
 else {
      ret.put(Keys.MSG,langPropsService.get("activity1A0001CollectFailLabel"));
    }
  }
 else {
    ret.put(Keys.MSG,langPropsService.get("activity1A0001CollectSucc0Label"));
  }
  return ret;
}
