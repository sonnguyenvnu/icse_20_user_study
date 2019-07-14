private void generateChatHistoryBasic(){
  ArrayList<Message> messageList=new ArrayList<>();
  Calendar calendar=Calendar.getInstance();
  calendar.set(Calendar.DATE,-1);
  messageList.add(new Message("Hello",true,calendar.getTimeInMillis()));
  calendar.add(Calendar.MINUTE,10);
  messageList.add(new Message("Hey",false,calendar.getTimeInMillis()));
  mViewModel.setMessages(messageList);
}
