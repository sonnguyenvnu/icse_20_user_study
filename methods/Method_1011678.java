@Override public void change(@NotNull SNodeReference input,@NotNull SNodeReference output,@NotNull SNodeReference template){
  myStepChange.record(input,output,template);
}
