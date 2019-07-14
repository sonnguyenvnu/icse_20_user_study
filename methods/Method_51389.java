private void checkDefaults(List<E> defaults){
  for (  E elt : defaults) {
    module.checkValue(elt);
  }
}
