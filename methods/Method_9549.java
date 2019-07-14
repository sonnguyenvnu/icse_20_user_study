private void prepareLayouts(int move){
  int widht=AndroidUtilities.displaySize.x - AndroidUtilities.dp(24);
  if (move == 0) {
    reuseView(centerView);
    reuseView(leftView);
    reuseView(rightView);
    reuseButtonsView(centerButtonsView);
    reuseButtonsView(leftButtonsView);
    reuseButtonsView(rightButtonsView);
    for (int a=currentMessageNum - 1; a < currentMessageNum + 2; a++) {
      if (a == currentMessageNum - 1) {
        leftView=getViewForMessage(a,true);
        leftButtonsView=getButtonsViewForMessage(a,true);
      }
 else       if (a == currentMessageNum) {
        centerView=getViewForMessage(a,true);
        centerButtonsView=getButtonsViewForMessage(a,true);
      }
 else       if (a == currentMessageNum + 1) {
        rightView=getViewForMessage(a,true);
        rightButtonsView=getButtonsViewForMessage(a,true);
      }
    }
  }
 else   if (move == 1) {
    reuseView(rightView);
    reuseButtonsView(rightButtonsView);
    rightView=centerView;
    centerView=leftView;
    leftView=getViewForMessage(currentMessageNum - 1,true);
    rightButtonsView=centerButtonsView;
    centerButtonsView=leftButtonsView;
    leftButtonsView=getButtonsViewForMessage(currentMessageNum - 1,true);
  }
 else   if (move == 2) {
    reuseView(leftView);
    reuseButtonsView(leftButtonsView);
    leftView=centerView;
    centerView=rightView;
    rightView=getViewForMessage(currentMessageNum + 1,true);
    leftButtonsView=centerButtonsView;
    centerButtonsView=rightButtonsView;
    rightButtonsView=getButtonsViewForMessage(currentMessageNum + 1,true);
  }
 else   if (move == 3) {
    if (rightView != null) {
      float offset=rightView.getTranslationX();
      reuseView(rightView);
      if ((rightView=getViewForMessage(currentMessageNum + 1,false)) != null) {
        FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)rightView.getLayoutParams();
        layoutParams.width=widht;
        rightView.setLayoutParams(layoutParams);
        rightView.setTranslationX(offset);
        rightView.invalidate();
      }
    }
    if (rightButtonsView != null) {
      float offset=rightButtonsView.getTranslationX();
      reuseButtonsView(rightButtonsView);
      if ((rightButtonsView=getButtonsViewForMessage(currentMessageNum + 1,false)) != null) {
        rightButtonsView.setTranslationX(offset);
      }
    }
  }
 else   if (move == 4) {
    if (leftView != null) {
      float offset=leftView.getTranslationX();
      reuseView(leftView);
      if ((leftView=getViewForMessage(0,false)) != null) {
        FrameLayout.LayoutParams layoutParams=(FrameLayout.LayoutParams)leftView.getLayoutParams();
        layoutParams.width=widht;
        leftView.setLayoutParams(layoutParams);
        leftView.setTranslationX(offset);
        leftView.invalidate();
      }
    }
    if (leftButtonsView != null) {
      float offset=leftButtonsView.getTranslationX();
      reuseButtonsView(leftButtonsView);
      if ((leftButtonsView=getButtonsViewForMessage(0,false)) != null) {
        leftButtonsView.setTranslationX(offset);
      }
    }
  }
}
