public void indent() throws XMLStreamException {
  char[] indent=new char[depth * 2];
  Arrays.fill(indent,' ');
  underlying.writeCharacters(indent,0,indent.length);
}
