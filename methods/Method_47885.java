@NonNull private HashMap<Timestamp,ArrayList<Double>> getGroupedValues(DateUtils.TruncateField field){
  HashMap<Timestamp,ArrayList<Double>> groups=new HashMap<>();
  for (  Score s : this) {
    Timestamp groupTimestamp=new Timestamp(DateUtils.truncate(field,s.getTimestamp().getUnixTime()));
    if (!groups.containsKey(groupTimestamp))     groups.put(groupTimestamp,new ArrayList<>());
    groups.get(groupTimestamp).add(s.getValue());
  }
  return groups;
}
