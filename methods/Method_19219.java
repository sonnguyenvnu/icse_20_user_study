@OnBind static void onBind(ComponentContext c,EditTextWithEventHandlers editText,@Prop(optional=true) EditTextStateUpdatePolicy stateUpdatePolicy,@Prop(optional=true,varArg="textWatcher") List<TextWatcher> textWatchers){
  editText.setComponentContext(c);
  editText.setTextChangedEventHandler(com.facebook.litho.widget.EditText.getTextChangedEventHandler(c));
  editText.setSelectionChangedEventHandler(com.facebook.litho.widget.EditText.getSelectionChangedEventHandler(c));
  editText.setKeyUpEventHandler(com.facebook.litho.widget.EditText.getKeyUpEventHandler(c));
  editText.setStateUpdatePolicy(stateUpdatePolicy);
  editText.attachWatchers(textWatchers);
}
