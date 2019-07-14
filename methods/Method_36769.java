@Override public boolean enqueue(@NonNull List<Event> eventList){
  for (int i=0, size=eventList.size(); i < size; i++) {
    Message msg=obtainMessage();
    msg.obj=eventList.get(i);
    sendMessage(msg);
  }
  return true;
}
