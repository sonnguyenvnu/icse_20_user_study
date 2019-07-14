private void checkSendButton(boolean animated){
  if (editingMessageObject != null) {
    return;
  }
  if (isPaused) {
    animated=false;
  }
  CharSequence message=AndroidUtilities.getTrimmedString(messageEditText.getText());
  if (message.length() > 0 || forceShowSendButton || audioToSend != null || videoToSendMessageObject != null) {
    final String caption=messageEditText.getCaption();
    boolean showBotButton=caption != null && (sendButton.getVisibility() == VISIBLE || expandStickersButton.getVisibility() == VISIBLE);
    boolean showSendButton=caption == null && (cancelBotButton.getVisibility() == VISIBLE || expandStickersButton.getVisibility() == VISIBLE);
    if (audioVideoButtonContainer.getVisibility() == VISIBLE || showBotButton || showSendButton) {
      if (animated) {
        if (runningAnimationType == 1 && messageEditText.getCaption() == null || runningAnimationType == 3 && caption != null) {
          return;
        }
        if (runningAnimation != null) {
          runningAnimation.cancel();
          runningAnimation=null;
        }
        if (runningAnimation2 != null) {
          runningAnimation2.cancel();
          runningAnimation2=null;
        }
        if (attachLayout != null) {
          runningAnimation2=new AnimatorSet();
          runningAnimation2.playTogether(ObjectAnimator.ofFloat(attachLayout,View.ALPHA,0.0f),ObjectAnimator.ofFloat(attachLayout,View.SCALE_X,0.0f));
          runningAnimation2.setDuration(100);
          runningAnimation2.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              if (runningAnimation2 != null && runningAnimation2.equals(animation)) {
                attachLayout.setVisibility(GONE);
              }
            }
            @Override public void onAnimationCancel(            Animator animation){
              if (runningAnimation2 != null && runningAnimation2.equals(animation)) {
                runningAnimation2=null;
              }
            }
          }
);
          runningAnimation2.start();
          updateFieldRight(0);
          if (delegate != null && getVisibility() == VISIBLE) {
            delegate.onAttachButtonHidden();
          }
        }
        runningAnimation=new AnimatorSet();
        ArrayList<Animator> animators=new ArrayList<>();
        if (audioVideoButtonContainer.getVisibility() == VISIBLE) {
          animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.SCALE_X,0.1f));
          animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.SCALE_Y,0.1f));
          animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.ALPHA,0.0f));
        }
        if (expandStickersButton.getVisibility() == VISIBLE) {
          animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.SCALE_X,0.1f));
          animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.SCALE_Y,0.1f));
          animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.ALPHA,0.0f));
        }
        if (showBotButton) {
          animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_X,0.1f));
          animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_Y,0.1f));
          animators.add(ObjectAnimator.ofFloat(sendButton,View.ALPHA,0.0f));
        }
 else         if (showSendButton) {
          animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_X,0.1f));
          animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_Y,0.1f));
          animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.ALPHA,0.0f));
        }
        if (caption != null) {
          runningAnimationType=3;
          animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_X,1.0f));
          animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_Y,1.0f));
          animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.ALPHA,1.0f));
          cancelBotButton.setVisibility(VISIBLE);
        }
 else {
          runningAnimationType=1;
          animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_X,1.0f));
          animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_Y,1.0f));
          animators.add(ObjectAnimator.ofFloat(sendButton,View.ALPHA,1.0f));
          sendButton.setVisibility(VISIBLE);
        }
        runningAnimation.playTogether(animators);
        runningAnimation.setDuration(150);
        runningAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            if (runningAnimation != null && runningAnimation.equals(animation)) {
              if (caption != null) {
                cancelBotButton.setVisibility(VISIBLE);
                sendButton.setVisibility(GONE);
              }
 else {
                sendButton.setVisibility(VISIBLE);
                cancelBotButton.setVisibility(GONE);
              }
              audioVideoButtonContainer.setVisibility(GONE);
              expandStickersButton.setVisibility(GONE);
              runningAnimation=null;
              runningAnimationType=0;
            }
          }
          @Override public void onAnimationCancel(          Animator animation){
            if (runningAnimation != null && runningAnimation.equals(animation)) {
              runningAnimation=null;
            }
          }
        }
);
        runningAnimation.start();
      }
 else {
        audioVideoButtonContainer.setScaleX(0.1f);
        audioVideoButtonContainer.setScaleY(0.1f);
        audioVideoButtonContainer.setAlpha(0.0f);
        if (caption != null) {
          sendButton.setScaleX(0.1f);
          sendButton.setScaleY(0.1f);
          sendButton.setAlpha(0.0f);
          cancelBotButton.setScaleX(1.0f);
          cancelBotButton.setScaleY(1.0f);
          cancelBotButton.setAlpha(1.0f);
          cancelBotButton.setVisibility(VISIBLE);
          sendButton.setVisibility(GONE);
        }
 else {
          cancelBotButton.setScaleX(0.1f);
          cancelBotButton.setScaleY(0.1f);
          cancelBotButton.setAlpha(0.0f);
          sendButton.setScaleX(1.0f);
          sendButton.setScaleY(1.0f);
          sendButton.setAlpha(1.0f);
          sendButton.setVisibility(VISIBLE);
          cancelBotButton.setVisibility(GONE);
        }
        audioVideoButtonContainer.setVisibility(GONE);
        if (attachLayout != null) {
          attachLayout.setVisibility(GONE);
          if (delegate != null && getVisibility() == VISIBLE) {
            delegate.onAttachButtonHidden();
          }
          updateFieldRight(0);
        }
      }
    }
  }
 else   if (emojiView != null && emojiViewVisible && (stickersTabOpen || emojiTabOpen && searchingType == 2) && !AndroidUtilities.isInMultiwindow) {
    if (animated) {
      if (runningAnimationType == 4) {
        return;
      }
      if (runningAnimation != null) {
        runningAnimation.cancel();
        runningAnimation=null;
      }
      if (runningAnimation2 != null) {
        runningAnimation2.cancel();
        runningAnimation2=null;
      }
      if (attachLayout != null) {
        attachLayout.setVisibility(VISIBLE);
        runningAnimation2=new AnimatorSet();
        runningAnimation2.playTogether(ObjectAnimator.ofFloat(attachLayout,View.ALPHA,1.0f),ObjectAnimator.ofFloat(attachLayout,View.SCALE_X,1.0f));
        runningAnimation2.setDuration(100);
        runningAnimation2.start();
        updateFieldRight(1);
        if (getVisibility() == VISIBLE) {
          delegate.onAttachButtonShow();
        }
      }
      expandStickersButton.setVisibility(VISIBLE);
      runningAnimation=new AnimatorSet();
      runningAnimationType=4;
      ArrayList<Animator> animators=new ArrayList<>();
      animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.SCALE_X,1.0f));
      animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.SCALE_Y,1.0f));
      animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.ALPHA,1.0f));
      if (cancelBotButton.getVisibility() == VISIBLE) {
        animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_X,0.1f));
        animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_Y,0.1f));
        animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.ALPHA,0.0f));
      }
 else       if (audioVideoButtonContainer.getVisibility() == VISIBLE) {
        animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.SCALE_X,0.1f));
        animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.SCALE_Y,0.1f));
        animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.ALPHA,0.0f));
      }
 else {
        animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_X,0.1f));
        animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_Y,0.1f));
        animators.add(ObjectAnimator.ofFloat(sendButton,View.ALPHA,0.0f));
      }
      runningAnimation.playTogether(animators);
      runningAnimation.setDuration(150);
      runningAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (runningAnimation != null && runningAnimation.equals(animation)) {
            sendButton.setVisibility(GONE);
            cancelBotButton.setVisibility(GONE);
            audioVideoButtonContainer.setVisibility(GONE);
            expandStickersButton.setVisibility(VISIBLE);
            runningAnimation=null;
            runningAnimationType=0;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (runningAnimation != null && runningAnimation.equals(animation)) {
            runningAnimation=null;
          }
        }
      }
);
      runningAnimation.start();
    }
 else {
      sendButton.setScaleX(0.1f);
      sendButton.setScaleY(0.1f);
      sendButton.setAlpha(0.0f);
      cancelBotButton.setScaleX(0.1f);
      cancelBotButton.setScaleY(0.1f);
      cancelBotButton.setAlpha(0.0f);
      audioVideoButtonContainer.setScaleX(0.1f);
      audioVideoButtonContainer.setScaleY(0.1f);
      audioVideoButtonContainer.setAlpha(0.0f);
      expandStickersButton.setScaleX(1.0f);
      expandStickersButton.setScaleY(1.0f);
      expandStickersButton.setAlpha(1.0f);
      cancelBotButton.setVisibility(GONE);
      sendButton.setVisibility(GONE);
      audioVideoButtonContainer.setVisibility(GONE);
      expandStickersButton.setVisibility(VISIBLE);
      if (attachLayout != null) {
        if (getVisibility() == VISIBLE) {
          delegate.onAttachButtonShow();
        }
        attachLayout.setVisibility(VISIBLE);
        updateFieldRight(1);
      }
    }
  }
 else   if (sendButton.getVisibility() == VISIBLE || cancelBotButton.getVisibility() == VISIBLE || expandStickersButton.getVisibility() == VISIBLE) {
    if (animated) {
      if (runningAnimationType == 2) {
        return;
      }
      if (runningAnimation != null) {
        runningAnimation.cancel();
        runningAnimation=null;
      }
      if (runningAnimation2 != null) {
        runningAnimation2.cancel();
        runningAnimation2=null;
      }
      if (attachLayout != null) {
        attachLayout.setVisibility(VISIBLE);
        runningAnimation2=new AnimatorSet();
        runningAnimation2.playTogether(ObjectAnimator.ofFloat(attachLayout,View.ALPHA,1.0f),ObjectAnimator.ofFloat(attachLayout,View.SCALE_X,1.0f));
        runningAnimation2.setDuration(100);
        runningAnimation2.start();
        updateFieldRight(1);
        if (getVisibility() == VISIBLE) {
          delegate.onAttachButtonShow();
        }
      }
      audioVideoButtonContainer.setVisibility(VISIBLE);
      runningAnimation=new AnimatorSet();
      runningAnimationType=2;
      ArrayList<Animator> animators=new ArrayList<>();
      animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.SCALE_X,1.0f));
      animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.SCALE_Y,1.0f));
      animators.add(ObjectAnimator.ofFloat(audioVideoButtonContainer,View.ALPHA,1.0f));
      if (cancelBotButton.getVisibility() == VISIBLE) {
        animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_X,0.1f));
        animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.SCALE_Y,0.1f));
        animators.add(ObjectAnimator.ofFloat(cancelBotButton,View.ALPHA,0.0f));
      }
 else       if (expandStickersButton.getVisibility() == VISIBLE) {
        animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.SCALE_X,0.1f));
        animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.SCALE_Y,0.1f));
        animators.add(ObjectAnimator.ofFloat(expandStickersButton,View.ALPHA,0.0f));
      }
 else {
        animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_X,0.1f));
        animators.add(ObjectAnimator.ofFloat(sendButton,View.SCALE_Y,0.1f));
        animators.add(ObjectAnimator.ofFloat(sendButton,View.ALPHA,0.0f));
      }
      runningAnimation.playTogether(animators);
      runningAnimation.setDuration(150);
      runningAnimation.addListener(new AnimatorListenerAdapter(){
        @Override public void onAnimationEnd(        Animator animation){
          if (runningAnimation != null && runningAnimation.equals(animation)) {
            sendButton.setVisibility(GONE);
            cancelBotButton.setVisibility(GONE);
            audioVideoButtonContainer.setVisibility(VISIBLE);
            runningAnimation=null;
            runningAnimationType=0;
          }
        }
        @Override public void onAnimationCancel(        Animator animation){
          if (runningAnimation != null && runningAnimation.equals(animation)) {
            runningAnimation=null;
          }
        }
      }
);
      runningAnimation.start();
    }
 else {
      sendButton.setScaleX(0.1f);
      sendButton.setScaleY(0.1f);
      sendButton.setAlpha(0.0f);
      cancelBotButton.setScaleX(0.1f);
      cancelBotButton.setScaleY(0.1f);
      cancelBotButton.setAlpha(0.0f);
      expandStickersButton.setScaleX(0.1f);
      expandStickersButton.setScaleY(0.1f);
      expandStickersButton.setAlpha(0.0f);
      audioVideoButtonContainer.setScaleX(1.0f);
      audioVideoButtonContainer.setScaleY(1.0f);
      audioVideoButtonContainer.setAlpha(1.0f);
      cancelBotButton.setVisibility(GONE);
      sendButton.setVisibility(GONE);
      expandStickersButton.setVisibility(GONE);
      audioVideoButtonContainer.setVisibility(VISIBLE);
      if (attachLayout != null) {
        if (getVisibility() == VISIBLE) {
          delegate.onAttachButtonShow();
        }
        attachLayout.setVisibility(VISIBLE);
        updateFieldRight(1);
      }
    }
  }
}
