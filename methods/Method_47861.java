private void throwIfHasParent(){
  if (parent != null)   throw new IllegalStateException("Filtered lists cannot be modified directly. " + "You should modify the parent list instead.");
}
