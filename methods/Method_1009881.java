@SuppressWarnings("unchecked") @Override public <T extends Inventory>Iterable<T> slots(){
  if (this.forgeApi$slotIterator == null) {
    this.forgeApi$slotIterator=((SlotCollection)this.bridge$getSlotProvider()).getIterator(this);
  }
  return (Iterable<T>)this.forgeApi$slotIterator;
}
