@Override public void done(@NotNull T task,@NotNull GenerationStatus status){
  myCompletedTasks.add(new Pair<>(task,status));
  if (myDelegate != null) {
    myDelegate.done(task,status);
  }
}
