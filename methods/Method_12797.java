protected Object checked(@Nullable Object caller) throws ReflectedException {
  if (caller == null || mType.isInstance(caller)) {
    return caller;
  }
  throw new ReflectedException("Caller [" + caller + "] is not a instance of type [" + mType + "]!");
}
