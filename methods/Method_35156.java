@Override protected void pushToBackstack(@NonNull RouterTransaction entry){
  if (isDetachFrozen) {
    entry.controller.setDetachFrozen(true);
  }
  super.pushToBackstack(entry);
}
