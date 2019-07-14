@Override public void process(Text opener,Text closer,int delimiterCount){
  Node mention=new Mention();
  Node tmp=opener.getNext();
  while (tmp != null && tmp != closer) {
    Node next=tmp.getNext();
    mention.appendChild(tmp);
    tmp=next;
  }
  opener.insertAfter(mention);
}
