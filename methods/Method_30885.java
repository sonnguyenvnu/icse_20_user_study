private void updateOpenWithNative(){
  if (mOpenWithNativeMenuItem == null) {
    return;
  }
  mOpenWithNativeMenuItem.setChecked(Settings.OPEN_WITH_NATIVE_IN_WEBVIEW.getValue());
}
