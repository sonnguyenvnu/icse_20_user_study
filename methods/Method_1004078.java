public void ignore(final AbstractInsnNode fromInclusive,final AbstractInsnNode toInclusive){
  for (AbstractInsnNode i=fromInclusive; i != toInclusive; i=i.getNext()) {
    ignored.add(i);
  }
  ignored.add(toInclusive);
}
