protected String buildUrl(){
  return String.format("%s/bot%s/sendmessage?chat_id={chat_id}&text={text}&parse_mode={parse_mode}" + "&disable_notification={disable_notification}",this.apiUrl,this.authToken);
}
