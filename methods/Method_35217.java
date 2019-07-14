@NonNull public RouterTransaction pushChangeHandler(@Nullable ControllerChangeHandler handler){
  if (!attachedToRouter) {
    pushControllerChangeHandler=handler;
    return this;
  }
 else {
    throw new RuntimeException(getClass().getSimpleName() + "s can not be modified after being added to a Router.");
  }
}
