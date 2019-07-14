@OnCreateInitialState static void onCreateInitialState(final ComponentContext c,StateValue<AtomicReference<EditTextWithEventHandlers>> mountedView,StateValue<AtomicBoolean> configuredInitialText){
  mountedView.set(new AtomicReference<EditTextWithEventHandlers>());
  configuredInitialText.set(new AtomicBoolean());
}
