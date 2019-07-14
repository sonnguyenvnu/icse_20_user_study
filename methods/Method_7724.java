public static int getServiceMessageColor(){
  Integer serviceColor=currentColors.get(key_chat_serviceBackground);
  return serviceColor == null ? serviceMessageColor : serviceColor;
}
