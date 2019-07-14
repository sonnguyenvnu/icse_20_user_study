boolean contains(AccessOrder<?> e){
  return (e.getPreviousInAccessOrder() != null) || (e.getNextInAccessOrder() != null) || (e == first);
}
