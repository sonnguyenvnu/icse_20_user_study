@Override public boolean contains(Object e){
  if (!(e instanceof RequestLogAvailability)) {
    return false;
  }
  final int flags=((RequestLogAvailability)e).getterFlags();
  return (this.flags & flags) == flags;
}
