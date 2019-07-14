public boolean canUnarchive(){
  for (  Habit h : adapter.getSelected())   if (!h.isArchived())   return false;
  return true;
}
