public Terminated<Element> create(){
  if (ready() == false) {
    throw new IllegalStateException();
  }
  final String header=dataSource.next().getElement();
  final String name=header.length() > 2 ? header.substring(2) : null;
  final List<String> img=new ArrayList<String>();
  while (dataSource.peek(0).getElement().equals(">>") == false) {
    img.add(dataSource.next().getElement());
  }
  final Terminated<String> next=dataSource.next();
  final ElementImage element=new ElementImage(img);
  if (name != null) {
    dictionary.put(name,element);
  }
  return new Terminated<Element>(element,next.getTerminator());
}
