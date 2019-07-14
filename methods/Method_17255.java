boolean contains(WriteOrder<?> e){
  return (e.getPreviousInWriteOrder() != null) || (e.getNextInWriteOrder() != null) || (e == first);
}
