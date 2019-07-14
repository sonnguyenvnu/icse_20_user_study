private void captureValues(TransitionValues transitionValues){
  if (transitionValues.view instanceof TextView) {
    TextView textview=(TextView)transitionValues.view;
    transitionValues.values.put(PROPNAME_TEXT,textview.getText());
    if (textview instanceof EditText) {
      transitionValues.values.put(PROPNAME_TEXT_SELECTION_START,textview.getSelectionStart());
      transitionValues.values.put(PROPNAME_TEXT_SELECTION_END,textview.getSelectionEnd());
    }
    if (mChangeBehavior > CHANGE_BEHAVIOR_KEEP) {
      transitionValues.values.put(PROPNAME_TEXT_COLOR,textview.getCurrentTextColor());
    }
  }
}
