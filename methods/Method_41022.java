public Date runningSince(){
  if (initialStart == null)   return null;
  return new Date(initialStart.getTime());
}
