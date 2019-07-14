public String getClientPhone(){
synchronized (sync) {
    return currentUser != null && currentUser.phone != null ? currentUser.phone : "";
  }
}
