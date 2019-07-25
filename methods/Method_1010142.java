private void report(MessageKind kind,String text,@NotNull SModuleReference module){
  Message m=myFactory.prepare(kind,text,null);
  m.setHintObject(module);
  addMessage(m);
}
