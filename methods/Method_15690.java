default boolean validate(){
  if (!isNormal()) {
    throw new UnAuthorizedException(getState());
  }
  return true;
}
