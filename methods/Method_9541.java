private void switchToNextMessage(){
  if (popupMessages.size() > 1) {
    if (currentMessageNum < popupMessages.size() - 1) {
      currentMessageNum++;
    }
 else {
      currentMessageNum=0;
    }
    currentMessageObject=popupMessages.get(currentMessageNum);
    updateInterfaceForCurrentMessage(2);
    countText.setText(String.format("%d/%d",currentMessageNum + 1,popupMessages.size()));
  }
}
