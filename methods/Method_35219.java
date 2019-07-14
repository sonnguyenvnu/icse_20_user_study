@NonNull public RouterTransaction popChangeHandler(@Nullable ControllerChangeHandler handler){
  if (!attachedToRouter) {
    popControllerChangeHandler=handler;
    return this;
  }
 else {
    throw new RuntimeException(getClass().getSimpleName() + "s can not be modified after being added to a Router.");
  }
}
