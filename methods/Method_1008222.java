void halt(int status){
  AccessController.doPrivileged(new PrivilegedHaltAction(status));
}
