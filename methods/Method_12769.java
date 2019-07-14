public static ComponentName getComponent(Intent intent){
  if (intent == null) {
    return null;
  }
  if (isIntentFromPlugin(intent)) {
    return new ComponentName(intent.getStringExtra(Constants.KEY_TARGET_PACKAGE),intent.getStringExtra(Constants.KEY_TARGET_ACTIVITY));
  }
  return intent.getComponent();
}
