@SuppressWarnings("unchecked") @Override public <T extends Inventory>Iterable<T> slots(){
  if (this.forgeAPI$slotIterator == null) {
    this.forgeAPI$slotIterator=((SlotCollection)this.bridge$getSlotProvider()).getIterator(this);
  }
  return (Iterable<T>)this.forgeAPI$slotIterator;
}
