private void migrateToNewChat(MessageObject obj){
  if (parentLayout == null) {
    return;
  }
  final int channelId=obj.messageOwner.action.channel_id;
  final BaseFragment lastFragment=parentLayout.fragmentsStack.size() > 0 ? parentLayout.fragmentsStack.get(parentLayout.fragmentsStack.size() - 1) : null;
  int index=parentLayout.fragmentsStack.indexOf(ChatActivity.this);
  final ActionBarLayout actionBarLayout=parentLayout;
  if (index > 0 && !(lastFragment instanceof ChatActivity) && !(lastFragment instanceof ProfileActivity) && currentChat.creator) {
    for (int a=index, N=actionBarLayout.fragmentsStack.size() - 1; a < N; a++) {
      BaseFragment fragment=actionBarLayout.fragmentsStack.get(a);
      if (fragment instanceof ChatActivity) {
        final Bundle bundle=new Bundle();
        bundle.putInt("chat_id",channelId);
        actionBarLayout.addFragmentToStack(new ChatActivity(bundle),a);
        fragment.removeSelfFromStack();
      }
 else       if (fragment instanceof ProfileActivity) {
        Bundle args=new Bundle();
        args.putInt("chat_id",channelId);
        actionBarLayout.addFragmentToStack(new ProfileActivity(args),a);
        fragment.removeSelfFromStack();
      }
 else       if (fragment instanceof ChatEditActivity) {
        Bundle args=new Bundle();
        args.putInt("chat_id",channelId);
        actionBarLayout.addFragmentToStack(new ChatEditActivity(args),a);
        fragment.removeSelfFromStack();
      }
 else       if (fragment instanceof ChatUsersActivity) {
        ChatUsersActivity usersActivity=(ChatUsersActivity)fragment;
        if (!usersActivity.hasSelectType()) {
          Bundle args=fragment.getArguments();
          args.putInt("chat_id",channelId);
          actionBarLayout.addFragmentToStack(new ChatUsersActivity(args),a);
        }
        fragment.removeSelfFromStack();
      }
    }
  }
 else {
    AndroidUtilities.runOnUIThread(() -> {
      if (lastFragment != null) {
        NotificationCenter.getInstance(currentAccount).removeObserver(lastFragment,NotificationCenter.closeChats);
      }
      NotificationCenter.getInstance(currentAccount).postNotificationName(NotificationCenter.closeChats);
      final Bundle bundle=new Bundle();
      bundle.putInt("chat_id",obj.messageOwner.action.channel_id);
      actionBarLayout.presentFragment(new ChatActivity(bundle),true);
    }
);
  }
  AndroidUtilities.runOnUIThread(() -> MessagesController.getInstance(currentAccount).loadFullChat(channelId,0,true),1000);
}
