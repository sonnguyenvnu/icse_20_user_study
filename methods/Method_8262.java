private void addToSelectedMessages(MessageObject messageObject,boolean outside,boolean last){
  int prevCantForwardCount=cantForwardMessagesCount;
  if (messageObject != null) {
    int index=messageObject.getDialogId() == dialog_id ? 0 : 1;
    if (outside && messageObject.getGroupId() != 0) {
      boolean hasUnselected=false;
      MessageObject.GroupedMessages groupedMessages=groupedMessagesMap.get(messageObject.getGroupId());
      if (groupedMessages != null) {
        int lastNum=0;
        for (int a=0; a < groupedMessages.messages.size(); a++) {
          MessageObject message=groupedMessages.messages.get(a);
          if (selectedMessagesIds[index].indexOfKey(message.getId()) < 0) {
            hasUnselected=true;
            lastNum=a;
          }
        }
        for (int a=0; a < groupedMessages.messages.size(); a++) {
          MessageObject message=groupedMessages.messages.get(a);
          if (hasUnselected) {
            if (selectedMessagesIds[index].indexOfKey(message.getId()) < 0) {
              addToSelectedMessages(message,false,a == lastNum);
            }
          }
 else {
            addToSelectedMessages(message,false,a == groupedMessages.messages.size() - 1);
          }
        }
      }
      return;
    }
    if (selectedMessagesIds[index].indexOfKey(messageObject.getId()) >= 0) {
      selectedMessagesIds[index].remove(messageObject.getId());
      if (messageObject.type == 0 || messageObject.caption != null) {
        selectedMessagesCanCopyIds[index].remove(messageObject.getId());
      }
      if (messageObject.isSticker()) {
        selectedMessagesCanStarIds[index].remove(messageObject.getId());
      }
      if (messageObject.canEditMessage(currentChat)) {
        canEditMessagesCount--;
      }
      if (!messageObject.canDeleteMessage(currentChat)) {
        cantDeleteMessagesCount--;
      }
      if (!messageObject.canForwardMessage()) {
        cantForwardMessagesCount--;
      }
    }
 else {
      if (selectedMessagesIds[0].size() + selectedMessagesIds[1].size() >= 100) {
        return;
      }
      selectedMessagesIds[index].put(messageObject.getId(),messageObject);
      if (messageObject.type == 0 || messageObject.caption != null) {
        selectedMessagesCanCopyIds[index].put(messageObject.getId(),messageObject);
      }
      if (messageObject.isSticker()) {
        selectedMessagesCanStarIds[index].put(messageObject.getId(),messageObject);
      }
      if (messageObject.canEditMessage(currentChat)) {
        canEditMessagesCount++;
      }
      if (!messageObject.canDeleteMessage(currentChat)) {
        cantDeleteMessagesCount++;
      }
      if (!messageObject.canForwardMessage()) {
        cantForwardMessagesCount++;
      }
    }
  }
  if (forwardButtonAnimation != null) {
    forwardButtonAnimation.cancel();
    forwardButtonAnimation=null;
  }
  if (last && actionBar.isActionModeShowed()) {
    int selectedCount=selectedMessagesIds[0].size() + selectedMessagesIds[1].size();
    if (selectedCount == 0) {
      hideActionMode();
      updatePinnedMessageView(true);
    }
 else {
      ActionBarMenuItem copyItem=actionBar.createActionMode().getItem(copy);
      ActionBarMenuItem starItem=actionBar.createActionMode().getItem(star);
      ActionBarMenuItem editItem=actionBar.createActionMode().getItem(edit);
      ActionBarMenuItem forwardItem=actionBar.createActionMode().getItem(forward);
      if (prevCantForwardCount == 0 && cantForwardMessagesCount != 0 || prevCantForwardCount != 0 && cantForwardMessagesCount == 0) {
        forwardButtonAnimation=new AnimatorSet();
        ArrayList<Animator> animators=new ArrayList<>();
        if (forwardItem != null) {
          forwardItem.setEnabled(cantForwardMessagesCount == 0);
          animators.add(ObjectAnimator.ofFloat(forwardItem,View.ALPHA,cantForwardMessagesCount == 0 ? 1.0f : 0.5f));
        }
        if (forwardButton != null) {
          forwardButton.setEnabled(cantForwardMessagesCount == 0);
          animators.add(ObjectAnimator.ofFloat(forwardButton,View.ALPHA,cantForwardMessagesCount == 0 ? 1.0f : 0.5f));
        }
        forwardButtonAnimation.playTogether(animators);
        forwardButtonAnimation.setDuration(100);
        forwardButtonAnimation.addListener(new AnimatorListenerAdapter(){
          @Override public void onAnimationEnd(          Animator animation){
            forwardButtonAnimation=null;
          }
        }
);
        forwardButtonAnimation.start();
      }
 else {
        if (forwardItem != null) {
          forwardItem.setEnabled(cantForwardMessagesCount == 0);
          forwardItem.setAlpha(cantForwardMessagesCount == 0 ? 1.0f : 0.5f);
        }
        if (forwardButton != null) {
          forwardButton.setEnabled(cantForwardMessagesCount == 0);
          forwardButton.setAlpha(cantForwardMessagesCount == 0 ? 1.0f : 0.5f);
        }
      }
      int copyVisible=copyItem.getVisibility();
      int starVisible=starItem.getVisibility();
      copyItem.setVisibility(selectedMessagesCanCopyIds[0].size() + selectedMessagesCanCopyIds[1].size() != 0 ? View.VISIBLE : View.GONE);
      starItem.setVisibility(DataQuery.getInstance(currentAccount).canAddStickerToFavorites() && (selectedMessagesCanStarIds[0].size() + selectedMessagesCanStarIds[1].size()) == selectedCount ? View.VISIBLE : View.GONE);
      int newCopyVisible=copyItem.getVisibility();
      int newStarVisible=starItem.getVisibility();
      actionBar.createActionMode().getItem(delete).setVisibility(cantDeleteMessagesCount == 0 ? View.VISIBLE : View.GONE);
      hasUnfavedSelected=false;
      for (int a=0; a < 2; a++) {
        for (int b=0; b < selectedMessagesCanStarIds[a].size(); b++) {
          MessageObject msg=selectedMessagesCanStarIds[a].valueAt(b);
          if (!DataQuery.getInstance(currentAccount).isStickerInFavorites(msg.getDocument())) {
            hasUnfavedSelected=true;
            break;
          }
        }
        if (hasUnfavedSelected) {
          break;
        }
      }
      starItem.setIcon(hasUnfavedSelected ? R.drawable.msg_fave : R.drawable.msg_unfave);
      final int newEditVisibility=canEditMessagesCount == 1 && selectedCount == 1 ? View.VISIBLE : View.GONE;
      if (replyButton != null) {
        boolean allowChatActions=true;
        if (currentEncryptedChat != null && AndroidUtilities.getPeerLayerVersion(currentEncryptedChat.layer) < 46 || isBroadcast || bottomOverlayChat != null && bottomOverlayChat.getVisibility() == View.VISIBLE || currentChat != null && (ChatObject.isNotInChat(currentChat) || ChatObject.isChannel(currentChat) && !ChatObject.canPost(currentChat) && !currentChat.megagroup || !ChatObject.canSendMessages(currentChat))) {
          allowChatActions=false;
        }
        int newVisibility;
        if (!allowChatActions || selectedCount == 0 || selectedMessagesIds[0].size() != 0 && selectedMessagesIds[1].size() != 0) {
          newVisibility=View.GONE;
        }
 else         if (selectedCount == 1) {
          newVisibility=View.VISIBLE;
        }
 else {
          newVisibility=View.VISIBLE;
          long lastGroupId=0;
          for (int a=0; a < 2; a++) {
            for (int b=0, N=selectedMessagesIds[a].size(); b < N; b++) {
              MessageObject message=selectedMessagesIds[a].valueAt(b);
              long groupId=message.getGroupId();
              if (groupId == 0 || lastGroupId != 0 && lastGroupId != groupId) {
                newVisibility=View.GONE;
                break;
              }
              lastGroupId=groupId;
            }
            if (newVisibility == View.GONE) {
              break;
            }
          }
        }
        if (replyButton.getVisibility() != newVisibility) {
          if (replyButtonAnimation != null) {
            replyButtonAnimation.cancel();
          }
          replyButtonAnimation=new AnimatorSet();
          if (newVisibility == View.VISIBLE) {
            replyButton.setVisibility(newVisibility);
            replyButtonAnimation.playTogether(ObjectAnimator.ofFloat(replyButton,View.ALPHA,1.0f),ObjectAnimator.ofFloat(replyButton,View.SCALE_Y,1.0f));
          }
 else {
            replyButtonAnimation.playTogether(ObjectAnimator.ofFloat(replyButton,View.ALPHA,0.0f),ObjectAnimator.ofFloat(replyButton,View.SCALE_Y,0.0f));
          }
          replyButtonAnimation.setDuration(100);
          int newVisibilityFinal=newVisibility;
          replyButtonAnimation.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              if (replyButtonAnimation != null && replyButtonAnimation.equals(animation)) {
                if (newVisibilityFinal == View.GONE) {
                  replyButton.setVisibility(View.GONE);
                }
              }
            }
            @Override public void onAnimationCancel(            Animator animation){
              if (replyButtonAnimation != null && replyButtonAnimation.equals(animation)) {
                replyButtonAnimation=null;
              }
            }
          }
);
          replyButtonAnimation.start();
        }
      }
      if (editItem != null) {
        if (copyVisible != newCopyVisible || starVisible != newStarVisible) {
          if (newEditVisibility == View.VISIBLE) {
            editItem.setAlpha(1.0f);
            editItem.setScaleX(1.0f);
          }
 else {
            editItem.setAlpha(0.0f);
            editItem.setScaleX(0.0f);
          }
          editItem.setVisibility(newEditVisibility);
        }
 else         if (editItem.getVisibility() != newEditVisibility) {
          if (editButtonAnimation != null) {
            editButtonAnimation.cancel();
          }
          editButtonAnimation=new AnimatorSet();
          editItem.setPivotX(AndroidUtilities.dp(54));
          editItem.setPivotX(AndroidUtilities.dp(54));
          if (newEditVisibility == View.VISIBLE) {
            editItem.setVisibility(newEditVisibility);
            editButtonAnimation.playTogether(ObjectAnimator.ofFloat(editItem,View.ALPHA,1.0f),ObjectAnimator.ofFloat(editItem,View.SCALE_X,1.0f));
          }
 else {
            editButtonAnimation.playTogether(ObjectAnimator.ofFloat(editItem,View.ALPHA,0.0f),ObjectAnimator.ofFloat(editItem,View.SCALE_X,0.0f));
          }
          editButtonAnimation.setDuration(100);
          editButtonAnimation.addListener(new AnimatorListenerAdapter(){
            @Override public void onAnimationEnd(            Animator animation){
              if (editButtonAnimation != null && editButtonAnimation.equals(animation)) {
                if (newEditVisibility == View.GONE) {
                  editItem.setVisibility(View.GONE);
                }
              }
            }
            @Override public void onAnimationCancel(            Animator animation){
              if (editButtonAnimation != null && editButtonAnimation.equals(animation)) {
                editButtonAnimation=null;
              }
            }
          }
);
          editButtonAnimation.start();
        }
      }
    }
  }
}
