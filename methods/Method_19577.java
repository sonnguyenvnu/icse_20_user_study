@OnBindDynamicValue static void onBindTime(ClockView clockView,@Prop(dynamic=true) long time){
  clockView.setTime(time % ClockView.TWELVE_HOURS);
}
