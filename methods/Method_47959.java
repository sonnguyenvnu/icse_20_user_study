public void scheduleMinutesFromNow(Habit habit,long minutes){
  long now=applyTimezone(getLocalTime());
  long reminderTime=now + minutes * 60 * 1000;
  scheduleAtTime(habit,reminderTime);
}
