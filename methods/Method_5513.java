private void handleCtrl(byte cc1,byte cc2,boolean repeatedControlPossible){
  if (isRepeatable(cc1)) {
    if (repeatedControlPossible && repeatableControlCc1 == cc1 && repeatableControlCc2 == cc2) {
      return;
    }
 else {
      repeatableControlSet=true;
      repeatableControlCc1=cc1;
      repeatableControlCc2=cc2;
    }
  }
  if (isMidrowCtrlCode(cc1,cc2)) {
    handleMidrowCtrl(cc2);
  }
 else   if (isPreambleAddressCode(cc1,cc2)) {
    handlePreambleAddressCode(cc1,cc2);
  }
 else   if (isTabCtrlCode(cc1,cc2)) {
    currentCueBuilder.tabOffset=cc2 - 0x20;
  }
 else   if (isMiscCode(cc1,cc2)) {
    handleMiscCode(cc2);
  }
}
