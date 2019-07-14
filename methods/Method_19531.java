@OnCreateInitialState static void onCreateInitialState(ComponentContext c,StateValue<List<Message>> messages,StateValue<Integer> counter,@Prop List<Message> initialMessages){
  messages.set(initialMessages);
  counter.set(1);
}
