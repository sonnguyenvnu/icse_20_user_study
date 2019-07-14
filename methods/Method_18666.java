@Override public boolean isEquivalentTo(Component other){
  if (this == other) {
    return true;
  }
  if (other == null || getClass() != other.getClass()) {
    return false;
  }
  Wrapper wrapper=(Wrapper)other;
  if (this.getId() == wrapper.getId()) {
    return true;
  }
  if (delegate != null ? !delegate.equals(wrapper.delegate) : wrapper.delegate != null) {
    return false;
  }
  return true;
}
