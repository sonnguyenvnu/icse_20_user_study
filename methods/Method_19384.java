@OnCreateInitialState static void onCreateInitialState(ComponentContext c,@Prop String selectedOption,StateValue<String> selection){
  selection.set(selectedOption);
}
