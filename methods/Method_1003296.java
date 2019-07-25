@Override public void rename(String newName){
  super.rename(newName);
  if (constraints != null) {
    for (    Constraint constraint : constraints) {
      constraint.rebuild();
    }
  }
}
