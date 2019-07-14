private void createDateArray(int accountNum,TLRPC.TL_channelAdminLogEvent event,ArrayList<MessageObject> messageObjects,HashMap<String,ArrayList<MessageObject>> messagesByDays){
  ArrayList<MessageObject> dayArray=messagesByDays.get(dateKey);
  if (dayArray == null) {
    dayArray=new ArrayList<>();
    messagesByDays.put(dateKey,dayArray);
    TLRPC.TL_message dateMsg=new TLRPC.TL_message();
    dateMsg.message=LocaleController.formatDateChat(event.date);
    dateMsg.id=0;
    dateMsg.date=event.date;
    MessageObject dateObj=new MessageObject(accountNum,dateMsg,false);
    dateObj.type=10;
    dateObj.contentType=1;
    dateObj.isDateObject=true;
    messageObjects.add(dateObj);
  }
}
