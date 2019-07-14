protected void verifyElement(ConfigElement element){
  Preconditions.checkNotNull(element);
  Preconditions.checkArgument(element.getRoot().equals(root),"Configuration element is not associated with this configuration: %s",element);
}
