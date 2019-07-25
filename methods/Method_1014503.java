@Override public boolean enter(GameObject object){
  if (object instanceof ControllableUnit) {
    ((ControllableUnit)object).getCpu().getMemory().corrupt(corruptionBlockSize);
  }
  return false;
}
