@Override public boolean isPackagePrivate(){
  return !isPrivate() && !isPublic() && !isProtected();
}
