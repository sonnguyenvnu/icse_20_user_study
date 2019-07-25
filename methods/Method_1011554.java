@NotNull PausedJavaUiState paused(@NotNull Context context){
  return new PausedJavaUiState(context,myDebugSession);
}
