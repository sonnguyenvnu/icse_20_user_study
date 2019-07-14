public int getClientUserId(){
synchronized (sync) {
    return currentUser != null ? currentUser.id : 0;
  }
}
