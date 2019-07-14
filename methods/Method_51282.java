@Override public Set<NameDeclaration> addNameOccurrence(NameOccurrence occurrence){
  Set<NameDeclaration> result=new HashSet<>();
  for (  Map.Entry<NameDeclaration,List<NameOccurrence>> e : getDeclarations().entrySet()) {
    if (e.getKey().getImage().equals(occurrence.getImage())) {
      result.add(e.getKey());
      e.getValue().add(occurrence);
    }
  }
  return result;
}
