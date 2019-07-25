public static void log(Project p,MessageKind kind,String message){
  p.getComponent(MessagesViewTool.class).add(new Message(kind,message));
}
