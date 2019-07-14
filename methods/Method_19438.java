@OnCreateInitialState static void onCreateInitialState(final ComponentContext c,StateValue<AtomicReference<EditTextWithEventHandlers>> mountedView,StateValue<AtomicReference<CharSequence>> savedText,StateValue<Integer> measureSeqNumber,@Prop(optional=true,resType=ResType.STRING) CharSequence initialText){
  mountedView.set(new AtomicReference<EditTextWithEventHandlers>());
  measureSeqNumber.set(0);
  savedText.set(new AtomicReference<>(initialText));
}
