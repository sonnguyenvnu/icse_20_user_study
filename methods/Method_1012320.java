public void dismiss(){
  popupWindow.dismiss();
  variantPopup.dismiss();
  recentEmoji.persist();
  variantEmoji.persist();
  emojiResultReceiver.setReceiver(null);
  if (originalImeOptions != -1) {
    editText.setImeOptions(originalImeOptions);
    final InputMethodManager inputMethodManager=(InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
    if (inputMethodManager != null) {
      inputMethodManager.restartInput(editText);
    }
    if (SDK_INT >= O) {
      final AutofillManager autofillManager=context.getSystemService(AutofillManager.class);
      if (autofillManager != null) {
        autofillManager.cancel();
      }
    }
  }
}
