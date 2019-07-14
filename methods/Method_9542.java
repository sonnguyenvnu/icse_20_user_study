private void switchToPreviousMessage(){
  if (popupMessages.size() > 1) {
    if (currentMessageNum > 0) {
      currentMessageNum--;
    }
 else {
      currentMessageNum=popupMessages.size() - 1;
    }
    currentMessageObject=popupMessages.get(currentMessageNum);
    updateInterfaceForCurrentMessage(1);
    countText.setText(String.format("%d/%d",currentMessageNum + 1,popupMessages.size()));
  }
}
