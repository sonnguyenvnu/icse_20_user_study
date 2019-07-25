private void process(Ftile current){
  final Collection<Ftile> children=current.getMyChildren();
  for (  Ftile child : children) {
    setMyFather(child,current);
    process(child);
  }
}
