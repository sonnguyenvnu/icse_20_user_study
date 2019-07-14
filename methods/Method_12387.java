public Instance deregister(){
  if (this.isRegistered()) {
    return this.apply(new InstanceDeregisteredEvent(this.id,this.nextVersion()),true);
  }
  return this;
}
