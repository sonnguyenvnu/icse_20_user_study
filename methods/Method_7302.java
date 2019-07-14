public boolean isClientActivated(){
synchronized (sync) {
    return currentUser != null;
  }
}
