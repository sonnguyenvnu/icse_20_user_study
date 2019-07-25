protected final void report(MessageKind kind,String text,SNodeReference node,ProblemDescription... descriptions){
  if (descriptions == null) {
    report(kind,text,node);
    return;
  }
  List<Message> messages=new ArrayList<>(descriptions.length + 1);
  messages.add(myFactory.prepare(kind,text,node));
  for (  ProblemDescription d : descriptions) {
    if (d != null) {
      messages.add(myFactory.prepare(kind,"-- " + d.getMessage(),d.getNode()));
    }
  }
  for (  Message m : messages) {
    addMessage(m);
  }
}
