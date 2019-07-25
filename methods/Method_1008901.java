public void update(String uri,String localName,String qName){
  if (localName.contains(":")) {
    log.error("Unexpected localName " + localName);
    throw new java.lang.IllegalArgumentException("Unexpected localName " + localName);
  }
  QName qn=new QName(uri,localName);
  Integer v=occurrence.get(qn);
  if (v == null) {
    v=1;
  }
 else {
    v++;
  }
  occurrence.put(qn,v);
  current=qName;
  currentValue=v;
}
