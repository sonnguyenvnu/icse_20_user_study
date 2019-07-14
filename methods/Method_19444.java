@OnBind static void onBind(final ComponentContext c,EditTextWithEventHandlers editText,@Prop(optional=true,varArg="textWatcher") List<TextWatcher> textWatchers){
  editText.attachWatchers(textWatchers);
  editText.setComponentContext(c);
  editText.setTextChangedEventHandler(TextInput.getTextChangedEventHandler(c));
  editText.setSelectionChangedEventHandler(TextInput.getSelectionChangedEventHandler(c));
  editText.setKeyUpEventHandler(TextInput.getKeyUpEventHandler(c));
  editText.setEditorActionEventHandler(TextInput.getEditorActionEventHandler(c));
}
