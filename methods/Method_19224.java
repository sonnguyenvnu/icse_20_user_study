@OnUpdateState static void updateInput(StateValue<CharSequence> input,@Param CharSequence newInput){
  input.set(newInput);
}
