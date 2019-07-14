public static ControllerScopeProvider from(@NonNull Controller controller,@NonNull final ControllerEvent untilEvent){
  return new ControllerScopeProvider(controller,new CorrespondingEventsFunction<ControllerEvent>(){
    @Override public ControllerEvent apply(    ControllerEvent controllerEvent){
      return untilEvent;
    }
  }
);
}
