@Override protected TriggerPropertyBundle getTriggerPropertyBundle(SimplePropertiesTriggerProperties props){
  int repeatCount=(int)props.getLong1();
  int interval=props.getInt1();
  String intervalUnitStr=props.getString1();
  String daysOfWeekStr=props.getString2();
  String timeOfDayStr=props.getString3();
  IntervalUnit intervalUnit=IntervalUnit.valueOf(intervalUnitStr);
  DailyTimeIntervalScheduleBuilder scheduleBuilder=DailyTimeIntervalScheduleBuilder.dailyTimeIntervalSchedule().withInterval(interval,intervalUnit).withRepeatCount(repeatCount);
  if (daysOfWeekStr != null) {
    Set<Integer> daysOfWeek=new HashSet<Integer>();
    String[] nums=daysOfWeekStr.split(",");
    if (nums.length > 0) {
      for (      String num : nums) {
        daysOfWeek.add(Integer.parseInt(num));
      }
      scheduleBuilder.onDaysOfTheWeek(daysOfWeek);
    }
  }
 else {
    scheduleBuilder.onDaysOfTheWeek(DailyTimeIntervalScheduleBuilder.ALL_DAYS_OF_THE_WEEK);
  }
  if (timeOfDayStr != null) {
    String[] nums=timeOfDayStr.split(",");
    TimeOfDay startTimeOfDay;
    if (nums.length >= 3) {
      int hour=Integer.parseInt(nums[0]);
      int min=Integer.parseInt(nums[1]);
      int sec=Integer.parseInt(nums[2]);
      startTimeOfDay=new TimeOfDay(hour,min,sec);
    }
 else {
      startTimeOfDay=TimeOfDay.hourMinuteAndSecondOfDay(0,0,0);
    }
    scheduleBuilder.startingDailyAt(startTimeOfDay);
    TimeOfDay endTimeOfDay;
    if (nums.length >= 6) {
      int hour=Integer.parseInt(nums[3]);
      int min=Integer.parseInt(nums[4]);
      int sec=Integer.parseInt(nums[5]);
      endTimeOfDay=new TimeOfDay(hour,min,sec);
    }
 else {
      endTimeOfDay=TimeOfDay.hourMinuteAndSecondOfDay(23,59,59);
    }
    scheduleBuilder.endingDailyAt(endTimeOfDay);
  }
 else {
    scheduleBuilder.startingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(0,0,0));
    scheduleBuilder.endingDailyAt(TimeOfDay.hourMinuteAndSecondOfDay(23,59,59));
  }
  int timesTriggered=props.getInt2();
  String[] statePropertyNames={"timesTriggered"};
  Object[] statePropertyValues={timesTriggered};
  return new TriggerPropertyBundle(scheduleBuilder,statePropertyNames,statePropertyValues);
}
