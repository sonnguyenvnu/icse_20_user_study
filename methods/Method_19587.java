@OnUpdateState static void updateError(StateValue<Optional<Exception>> error,@Param Exception e){
  error.set(Optional.of(e));
}
