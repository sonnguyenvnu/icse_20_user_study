public boolean matches(ImportWrapper i){
  if (isStaticDemand) {
    if (allDemands.contains(i.fullname)) {
      return true;
    }
  }
  if (name == null && i.getName() == null) {
    return i.getFullName().equals(fullname);
  }
  return i.getName().equals(name);
}
