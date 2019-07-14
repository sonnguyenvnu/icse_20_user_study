public void onSnoozePressed(@NonNull Habit habit,final Context context){
  long delay=preferences.getSnoozeInterval();
  if (delay < 0)   showSnoozeDelayPicker(habit,context);
 else   scheduleReminderMinutesFromNow(habit,delay);
}
