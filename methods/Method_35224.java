public static ControllerScopeProvider from(@NonNull Controller controller){
  return new ControllerScopeProvider(controller,CORRESPONDING_EVENTS);
}
