public boolean grant(Permission desiredPermission){
  return permission == desiredPermission || permission == READWRITE;
}
