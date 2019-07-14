public static ControllerScopeProvider from(@NonNull Controller controller,@NonNull final CorrespondingEventsFunction<ControllerEvent> correspondingEventsFunction){
  return new ControllerScopeProvider(controller,correspondingEventsFunction);
}
