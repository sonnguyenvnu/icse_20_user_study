@Override public Inventory parent(){
  if (this.itemHandler instanceof Inventory) {
    return ((Inventory)this.itemHandler);
  }
  return new IItemHandlerAdapter(this.itemHandler);
}
