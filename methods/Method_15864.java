public static MapResponseMessage error(String message){
  MapResponseMessage mapResponseMessage=new MapResponseMessage();
  mapResponseMessage.message=message;
  return mapResponseMessage;
}
