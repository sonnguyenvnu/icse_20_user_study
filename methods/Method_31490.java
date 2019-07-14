@Override protected void doLock(){
  LOG.debug("Unable to lock " + this + " as SQLite does not support locking. No concurrent migration supported.");
}
