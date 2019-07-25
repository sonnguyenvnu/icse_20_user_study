@NotNull UpdateInfoNode detach(){
  if (getParent() != null) {
    detachFromParent();
  }
  return this;
}
