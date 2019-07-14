public E choiceFrom(String label){
  E result=choicesByLabel.get(label);
  if (result != null) {
    return result;
  }
  throw new IllegalArgumentException(label);
}
