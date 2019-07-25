@Override public boolean accepts(@NotNull Project project){
  return Settings.getInstance(project).profilerLocalEnabled;
}
