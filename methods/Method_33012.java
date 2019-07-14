private JFXAlertAnimation getCurrentAnimation(){
  JFXAlertAnimation usedAnimation=getAnimation();
  usedAnimation=usedAnimation == null ? JFXAlertAnimation.NO_ANIMATION : usedAnimation;
  return usedAnimation;
}
