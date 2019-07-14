final void setDetachFrozen(boolean frozen){
  isDetachFrozen=frozen;
  for (  RouterTransaction transaction : backstack) {
    transaction.controller.setDetachFrozen(frozen);
  }
}
