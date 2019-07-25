protected final void report(MessageKind kind,String text,SNodeReference node){
  addMessage(myFactory.prepare(kind,text == null ? "" : text,node));
}
