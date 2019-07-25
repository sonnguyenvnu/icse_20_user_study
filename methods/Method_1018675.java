@Override public boolean equals(Object obj){
  if (obj == this) {
    return true;
  }
  if (!this.getClass().isInstance(obj)) {
    return false;
  }
  return (getClass().cast(obj)).value() == this.value();
}
