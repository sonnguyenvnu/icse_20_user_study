@OnUpdateState static void updateLoadingState(StateValue<LoadingState> loadingState,@Param LoadingState currentLoadingState){
  loadingState.set(currentLoadingState);
}
