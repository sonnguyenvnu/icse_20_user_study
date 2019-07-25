public boolean matches(LinkedElement tested){
  if (network != null && network != tested.getNetwork()) {
    return false;
  }
  return elements.contains(tested.getElement().getName());
}
