/** 
 * Create a tree for deciding what keys can legally be typed.
 */
private void generateLegalTimesTree(){
  int k0=KeyEvent.KEYCODE_0;
  int k1=KeyEvent.KEYCODE_1;
  int k2=KeyEvent.KEYCODE_2;
  int k3=KeyEvent.KEYCODE_3;
  int k4=KeyEvent.KEYCODE_4;
  int k5=KeyEvent.KEYCODE_5;
  int k6=KeyEvent.KEYCODE_6;
  int k7=KeyEvent.KEYCODE_7;
  int k8=KeyEvent.KEYCODE_8;
  int k9=KeyEvent.KEYCODE_9;
  mLegalTimesTree=new Node();
  if (mIs24HourMode) {
    Node minuteFirstDigit=new Node(k0,k1,k2,k3,k4,k5);
    Node minuteSecondDigit=new Node(k0,k1,k2,k3,k4,k5,k6,k7,k8,k9);
    minuteFirstDigit.addChild(minuteSecondDigit);
    Node firstDigit=new Node(k0,k1);
    mLegalTimesTree.addChild(firstDigit);
    Node secondDigit=new Node(k0,k1,k2,k3,k4,k5);
    firstDigit.addChild(secondDigit);
    secondDigit.addChild(minuteFirstDigit);
    Node thirdDigit=new Node(k6,k7,k8,k9);
    secondDigit.addChild(thirdDigit);
    secondDigit=new Node(k6,k7,k8,k9);
    firstDigit.addChild(secondDigit);
    secondDigit.addChild(minuteFirstDigit);
    firstDigit=new Node(k2);
    mLegalTimesTree.addChild(firstDigit);
    secondDigit=new Node(k0,k1,k2,k3);
    firstDigit.addChild(secondDigit);
    secondDigit.addChild(minuteFirstDigit);
    secondDigit=new Node(k4,k5);
    firstDigit.addChild(secondDigit);
    secondDigit.addChild(minuteSecondDigit);
    firstDigit=new Node(k3,k4,k5,k6,k7,k8,k9);
    mLegalTimesTree.addChild(firstDigit);
    firstDigit.addChild(minuteFirstDigit);
  }
 else {
    Node ampm=new Node(getAmOrPmKeyCode(AM),getAmOrPmKeyCode(PM));
    Node firstDigit=new Node(k1);
    mLegalTimesTree.addChild(firstDigit);
    firstDigit.addChild(ampm);
    Node secondDigit=new Node(k0,k1,k2);
    firstDigit.addChild(secondDigit);
    secondDigit.addChild(ampm);
    Node thirdDigit=new Node(k0,k1,k2,k3,k4,k5);
    secondDigit.addChild(thirdDigit);
    thirdDigit.addChild(ampm);
    Node fourthDigit=new Node(k0,k1,k2,k3,k4,k5,k6,k7,k8,k9);
    thirdDigit.addChild(fourthDigit);
    fourthDigit.addChild(ampm);
    thirdDigit=new Node(k6,k7,k8,k9);
    secondDigit.addChild(thirdDigit);
    thirdDigit.addChild(ampm);
    secondDigit=new Node(k3,k4,k5);
    firstDigit.addChild(secondDigit);
    thirdDigit=new Node(k0,k1,k2,k3,k4,k5,k6,k7,k8,k9);
    secondDigit.addChild(thirdDigit);
    thirdDigit.addChild(ampm);
    firstDigit=new Node(k2,k3,k4,k5,k6,k7,k8,k9);
    mLegalTimesTree.addChild(firstDigit);
    firstDigit.addChild(ampm);
    secondDigit=new Node(k0,k1,k2,k3,k4,k5);
    firstDigit.addChild(secondDigit);
    thirdDigit=new Node(k0,k1,k2,k3,k4,k5,k6,k7,k8,k9);
    secondDigit.addChild(thirdDigit);
    thirdDigit.addChild(ampm);
  }
}
