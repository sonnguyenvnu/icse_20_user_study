public Terminated<Element> create(){
  if (ready() == false) {
    throw new IllegalStateException();
  }
  final Terminated<String> next=dataSource.next();
  String name=next.getElement();
  name=name.substring(2,name.length() - 2);
  final Element retrieve=dictionary.get(name);
  if (retrieve == null) {
    throw new IllegalArgumentException("Cannot retrieve " + name);
  }
  return new Terminated<Element>(retrieve,next.getTerminator());
}
