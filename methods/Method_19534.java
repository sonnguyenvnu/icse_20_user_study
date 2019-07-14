@OnUpdateState static void onUpdateList(StateValue<List<Message>> messages,StateValue<Integer> counter,@Param boolean adding,@Param int initialMessagesSize){
  final ArrayList<Message> updatedMessageList=new ArrayList<>(messages.get());
  int counterValue=counter.get();
  if (adding) {
    updatedMessageList.add(1,new Message(true,"Just Added #" + counterValue,true,"Recently",true));
    counter.set(counterValue + 1);
  }
 else   if (initialMessagesSize < updatedMessageList.size()) {
    updatedMessageList.remove(1);
  }
  messages.set(updatedMessageList);
}
