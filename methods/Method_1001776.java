public Terminated<Element> create(){
  if (ready() == false) {
    throw new IllegalStateException();
  }
  final Terminated<String> next=dataSource.next();
  final String text=next.getElement();
  return new Terminated<Element>(new ElementLine(text.charAt(0)),next.getTerminator());
}
