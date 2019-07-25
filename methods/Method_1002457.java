final String message(List<?> path,String format,Object... args){
  Message message=new Message(path.toArray(),format,args);
  return message.toString();
}
