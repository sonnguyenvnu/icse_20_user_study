private void updateBotButton(){
  if (botButton == null) {
    return;
  }
  if (hasBotCommands || botReplyMarkup != null) {
    if (botButton.getVisibility() != VISIBLE) {
      botButton.setVisibility(VISIBLE);
    }
    if (botReplyMarkup != null) {
      if (isPopupShowing() && currentPopupContentType == 1) {
        botButton.setImageResource(R.drawable.input_keyboard);
        botButton.setContentDescription(LocaleController.getString("AccDescrShowKeyboard",R.string.AccDescrShowKeyboard));
      }
 else {
        botButton.setImageResource(R.drawable.input_bot2);
        botButton.setContentDescription(LocaleController.getString("AccDescrBotKeyboard",R.string.AccDescrBotKeyboard));
      }
    }
 else {
      botButton.setImageResource(R.drawable.input_bot1);
      botButton.setContentDescription(LocaleController.getString("AccDescrBotCommands",R.string.AccDescrBotCommands));
    }
  }
 else {
    botButton.setVisibility(GONE);
  }
  updateFieldRight(2);
  attachLayout.setPivotX(AndroidUtilities.dp((botButton == null || botButton.getVisibility() == GONE) && (notifyButton == null || notifyButton.getVisibility() == GONE) ? 48 : 96));
}
