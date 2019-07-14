@NonNull public List<Checkmark> groupBy(DateUtils.TruncateField field){
  List<Checkmark> checks=getAll();
  int count=0;
  Timestamp truncatedTimestamps[]=new Timestamp[checks.size()];
  int values[]=new int[checks.size()];
  for (  Checkmark rep : checks) {
    Timestamp tt=rep.getTimestamp().truncate(field);
    if (count == 0 || !truncatedTimestamps[count - 1].equals(tt))     truncatedTimestamps[count++]=tt;
    if (habit.isNumerical())     values[count - 1]+=rep.getValue();
 else     if (rep.getValue() == Checkmark.CHECKED_EXPLICITLY)     values[count - 1]+=1000;
  }
  ArrayList<Checkmark> groupedCheckmarks=new ArrayList<>();
  for (int i=0; i < count; i++) {
    Checkmark rep=new Checkmark(truncatedTimestamps[i],values[i]);
    groupedCheckmarks.add(rep);
  }
  return groupedCheckmarks;
}
