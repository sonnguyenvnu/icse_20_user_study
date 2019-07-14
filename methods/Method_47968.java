private void reshowAll(){
  for (  Habit habit : active.keySet()) {
    NotificationData data=active.get(habit);
    taskRunner.execute(new ShowNotificationTask(habit,data));
  }
}
