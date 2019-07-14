/** 
 * Daily checkin.
 * @param userId the specified user id
 * @return {@code Random int} if checkin succeeded, returns {@code Integer.MIN_VALUE} otherwise
 */
public synchronized int dailyCheckin(final String userId){
  if (activityQueryService.isCheckedinToday(userId)) {
    return Integer.MIN_VALUE;
  }
  final Random random=new Random();
  final int sum=random.nextInt(Pointtransfer.TRANSFER_SUM_C_ACTIVITY_CHECKIN_MAX) % (Pointtransfer.TRANSFER_SUM_C_ACTIVITY_CHECKIN_MAX - Pointtransfer.TRANSFER_SUM_C_ACTIVITY_CHECKIN_MIN + 1) + Pointtransfer.TRANSFER_SUM_C_ACTIVITY_CHECKIN_MIN;
  final boolean succ=null != pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_CHECKIN,sum,userId,System.currentTimeMillis(),"");
  if (!succ) {
    return Integer.MIN_VALUE;
  }
  try {
    final JSONObject user=userQueryService.getUser(userId);
    int currentStreakStart=user.optInt(UserExt.USER_CURRENT_CHECKIN_STREAK_START);
    int currentStreakEnd=user.optInt(UserExt.USER_CURRENT_CHECKIN_STREAK_END);
    final Date today=new Date();
    user.put(UserExt.USER_CHECKIN_TIME,today.getTime());
    final String datePattern="yyyyMMdd";
    final String todayStr=DateFormatUtils.format(today,datePattern);
    final int todayInt=Integer.valueOf(todayStr);
    if (0 == currentStreakStart) {
      user.put(UserExt.USER_CURRENT_CHECKIN_STREAK_START,todayInt);
      user.put(UserExt.USER_CURRENT_CHECKIN_STREAK_END,todayInt);
      user.put(UserExt.USER_LONGEST_CHECKIN_STREAK_START,todayInt);
      user.put(UserExt.USER_LONGEST_CHECKIN_STREAK_END,todayInt);
      user.put(UserExt.USER_CURRENT_CHECKIN_STREAK,1);
      user.put(UserExt.USER_LONGEST_CHECKIN_STREAK,1);
      userMgmtService.updateUser(userId,user);
      return sum;
    }
    final Date endDate=DateUtils.parseDate(String.valueOf(currentStreakEnd),new String[]{datePattern});
    final Date nextDate=DateUtils.addDays(endDate,1);
    if (!DateUtils.isSameDay(nextDate,today)) {
      user.put(UserExt.USER_CURRENT_CHECKIN_STREAK_START,todayInt);
    }
    user.put(UserExt.USER_CURRENT_CHECKIN_STREAK_END,todayInt);
    currentStreakStart=user.optInt(UserExt.USER_CURRENT_CHECKIN_STREAK_START);
    currentStreakEnd=user.optInt(UserExt.USER_CURRENT_CHECKIN_STREAK_END);
    final Date currentStreakStartDate=DateUtils.parseDate(String.valueOf(currentStreakStart),new String[]{datePattern});
    final Date currentStreakEndDate=DateUtils.parseDate(String.valueOf(currentStreakEnd),new String[]{datePattern});
    final int currentStreakDays=(int)((currentStreakEndDate.getTime() - currentStreakStartDate.getTime()) / 86400000) + 1;
    user.put(UserExt.USER_CURRENT_CHECKIN_STREAK,currentStreakDays);
    final int longestStreakStart=user.optInt(UserExt.USER_LONGEST_CHECKIN_STREAK_START);
    final int longestStreakEnd=user.optInt(UserExt.USER_LONGEST_CHECKIN_STREAK_END);
    final Date longestStreakStartDate=DateUtils.parseDate(String.valueOf(longestStreakStart),new String[]{datePattern});
    final Date longestStreakEndDate=DateUtils.parseDate(String.valueOf(longestStreakEnd),new String[]{datePattern});
    final int longestStreakDays=(int)((longestStreakEndDate.getTime() - longestStreakStartDate.getTime()) / 86400000) + 1;
    user.put(UserExt.USER_LONGEST_CHECKIN_STREAK,longestStreakDays);
    if (longestStreakDays < currentStreakDays) {
      user.put(UserExt.USER_LONGEST_CHECKIN_STREAK_START,currentStreakStart);
      user.put(UserExt.USER_LONGEST_CHECKIN_STREAK_END,currentStreakEnd);
      user.put(UserExt.USER_LONGEST_CHECKIN_STREAK,currentStreakDays);
    }
    userMgmtService.updateUser(userId,user);
    if (currentStreakDays > 0 && 0 == currentStreakDays % 10) {
      pointtransferMgmtService.transfer(Pointtransfer.ID_C_SYS,userId,Pointtransfer.TRANSFER_TYPE_C_ACTIVITY_CHECKIN_STREAK,Pointtransfer.TRANSFER_SUM_C_ACTIVITY_CHECKINT_STREAK,userId,System.currentTimeMillis(),"");
    }
    livenessMgmtService.incLiveness(userId,Liveness.LIVENESS_ACTIVITY);
    return sum;
  }
 catch (  final Exception e) {
    LOGGER.log(Level.ERROR,"Checkin streak error",e);
    return Integer.MIN_VALUE;
  }
}
