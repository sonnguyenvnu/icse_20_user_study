private void generateChatHistoryWithSensitiveContent(){
  ArrayList<Message> messageList=new ArrayList<>();
  Calendar calendar=Calendar.getInstance();
  calendar.set(Calendar.DATE,-1);
  messageList.add(new Message("Hi",false,calendar.getTimeInMillis()));
  calendar.add(Calendar.MINUTE,10);
  messageList.add(new Message("How are you?",true,calendar.getTimeInMillis()));
  calendar.add(Calendar.MINUTE,10);
  messageList.add(new Message("My cat died",false,calendar.getTimeInMillis()));
  mViewModel.setMessages(messageList);
}
