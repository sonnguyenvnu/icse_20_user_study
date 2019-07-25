@Override public void clear(ControllableUnit unit){
  if (unit instanceof Rechargeable) {
    ((Rechargeable)unit).storeEnergy(energy);
  }
}
