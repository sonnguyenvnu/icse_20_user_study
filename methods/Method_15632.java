default void assertHas(){
  if (!test()) {
    throw new AccessDenyException();
  }
}
