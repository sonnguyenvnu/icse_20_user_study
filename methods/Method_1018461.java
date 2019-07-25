@Override public void flush(){
  HashMap<TimeSlotProxy,TimeSlotKey> index=new HashMap<TimeSlotProxy,TimeSlotKey>();
  for (  TimeSlotProxy slot : backing) {
    index.put(slot,new TimeSlotKey(slot));
  }
  initialSchedule.removeAll(currentSchedule);
  for (Iterator<TimeSlotProxy> iterator=backing.iterator(); iterator.hasNext(); ) {
    TimeSlotProxy slot=iterator.next();
    TimeSlotKey key=index.get(slot);
    if (initialSchedule.contains(key)) {
      iterator.remove();
    }
  }
}
