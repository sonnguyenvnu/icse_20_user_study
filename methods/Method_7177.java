private void putToDelayedMessages(String location,DelayedMessage message){
  ArrayList<DelayedMessage> arrayList=delayedMessages.get(location);
  if (arrayList == null) {
    arrayList=new ArrayList<>();
    delayedMessages.put(location,arrayList);
  }
  arrayList.add(message);
}
