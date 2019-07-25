@Override public Iterator<Path> iterator(){
  return this.elements.stream().map(e -> new JcrPath(false,e)).collect(Collectors.<Path>toList()).iterator();
}
