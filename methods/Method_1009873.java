@Override public void clear(){
  if (this.inventory instanceof IItemHandlerModifiable) {
    for (int i=0; i < this.inventory.getSlots(); i++) {
      ((IItemHandlerModifiable)this.inventory).setStackInSlot(i,ItemStack.EMPTY);
    }
  }
}
