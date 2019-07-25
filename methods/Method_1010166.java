@Nullable public CheckpointState find(@NotNull CheckpointIdentity tp){
  for (  CheckpointState cps : myStates) {
    if (cps.getCheckpoint().equals(tp)) {
      return cps;
    }
  }
  return null;
}
