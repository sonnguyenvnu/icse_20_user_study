@Override public <T extends NameDeclaration>Map<T,List<NameOccurrence>> getDeclarations(Class<T> clazz){
  @SuppressWarnings("unchecked") Map<T,List<NameOccurrence>> result=(Map<T,List<NameOccurrence>>)nameDeclarations.get(clazz);
  if (result == null) {
    result=Collections.emptyMap();
  }
  return result;
}
