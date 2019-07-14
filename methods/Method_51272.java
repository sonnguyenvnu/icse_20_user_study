private ValueRepresentation getRepresentation(final PropertyDescriptor<?> descriptor,final Object value){
  if (descriptor.isMultiValue()) {
    final List<?> val=(List<?>)value;
    if (val.isEmpty()) {
      return EmptySequence.getInstance();
    }
    final Item[] converted=new Item[val.size()];
    for (int i=0; i < val.size(); i++) {
      converted[i]=getAtomicRepresentation(val.get(i));
    }
    return new SequenceExtent(converted);
  }
 else {
    return getAtomicRepresentation(value);
  }
}
