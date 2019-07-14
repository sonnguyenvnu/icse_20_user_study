public boolean matches(Habit habit){
  if (!isArchivedAllowed() && habit.isArchived())   return false;
  if (isReminderRequired() && !habit.hasReminder())   return false;
  if (!isCompletedAllowed() && habit.isCompletedToday())   return false;
  return true;
}
