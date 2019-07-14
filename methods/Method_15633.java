default void assertHas(Authentication authentication){
  if (!test(authentication)) {
    throw new AccessDenyException();
  }
}
