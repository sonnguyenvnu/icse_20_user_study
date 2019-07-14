public void searchUsernameOrHashtag(String text,int position,ArrayList<MessageObject> messageObjects,boolean usernameOnly){
  if (cancelDelayRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(cancelDelayRunnable);
    cancelDelayRunnable=null;
  }
  if (channelReqId != 0) {
    ConnectionsManager.getInstance(currentAccount).cancelRequest(channelReqId,true);
    channelReqId=0;
  }
  if (searchGlobalRunnable != null) {
    AndroidUtilities.cancelRunOnUIThread(searchGlobalRunnable);
    searchGlobalRunnable=null;
  }
  if (TextUtils.isEmpty(text)) {
    searchForContextBot(null,null);
    delegate.needChangePanelVisibility(false);
    lastText=null;
    return;
  }
  int searchPostion=position;
  if (text.length() > 0) {
    searchPostion--;
  }
  lastText=null;
  lastUsernameOnly=usernameOnly;
  StringBuilder result=new StringBuilder();
  int foundType=-1;
  if (!usernameOnly && needBotContext && text.charAt(0) == '@') {
    int index=text.indexOf(' ');
    int len=text.length();
    String username=null;
    String query=null;
    if (index > 0) {
      username=text.substring(1,index);
      query=text.substring(index + 1);
    }
 else     if (text.charAt(len - 1) == 't' && text.charAt(len - 2) == 'o' && text.charAt(len - 3) == 'b') {
      username=text.substring(1);
      query="";
    }
 else {
      searchForContextBot(null,null);
    }
    if (username != null && username.length() >= 1) {
      for (int a=1; a < username.length(); a++) {
        char ch=username.charAt(a);
        if (!(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == '_')) {
          username="";
          break;
        }
      }
    }
 else {
      username="";
    }
    searchForContextBot(username,query);
  }
 else {
    searchForContextBot(null,null);
  }
  if (foundContextBot != null) {
    return;
  }
  final MessagesController messagesController=MessagesController.getInstance(currentAccount);
  int dogPostion=-1;
  if (usernameOnly) {
    result.append(text.substring(1));
    resultStartPosition=0;
    resultLength=result.length();
    foundType=0;
  }
 else {
    for (int a=searchPostion; a >= 0; a--) {
      if (a >= text.length()) {
        continue;
      }
      char ch=text.charAt(a);
      if (a == 0 || text.charAt(a - 1) == ' ' || text.charAt(a - 1) == '\n') {
        if (ch == '@') {
          if (needUsernames || needBotContext && a == 0) {
            if (info == null && a != 0) {
              lastText=text;
              lastPosition=position;
              messages=messageObjects;
              delegate.needChangePanelVisibility(false);
              return;
            }
            dogPostion=a;
            foundType=0;
            resultStartPosition=a;
            resultLength=result.length() + 1;
            break;
          }
        }
 else         if (ch == '#') {
          if (searchAdapterHelper.loadRecentHashtags()) {
            foundType=1;
            resultStartPosition=a;
            resultLength=result.length() + 1;
            result.insert(0,ch);
            break;
          }
 else {
            lastText=text;
            lastPosition=position;
            messages=messageObjects;
            delegate.needChangePanelVisibility(false);
            return;
          }
        }
 else         if (a == 0 && botInfo != null && ch == '/') {
          foundType=2;
          resultStartPosition=a;
          resultLength=result.length() + 1;
          break;
        }
 else         if (ch == ':' && result.length() > 0) {
          boolean isNextPunctiationChar=punctuationsChars.indexOf(result.charAt(0)) >= 0;
          if (!isNextPunctiationChar || result.length() > 1) {
            foundType=3;
            resultStartPosition=a;
            resultLength=result.length() + 1;
            break;
          }
        }
      }
      result.insert(0,ch);
    }
  }
  if (foundType == -1) {
    delegate.needChangePanelVisibility(false);
    return;
  }
  if (foundType == 0) {
    final ArrayList<Integer> users=new ArrayList<>();
    for (int a=0; a < Math.min(100,messageObjects.size()); a++) {
      int from_id=messageObjects.get(a).messageOwner.from_id;
      if (!users.contains(from_id)) {
        users.add(from_id);
      }
    }
    final String usernameString=result.toString().toLowerCase();
    boolean hasSpace=usernameString.indexOf(' ') >= 0;
    ArrayList<TLRPC.User> newResult=new ArrayList<>();
    final SparseArray<TLRPC.User> newResultsHashMap=new SparseArray<>();
    final SparseArray<TLRPC.User> newMap=new SparseArray<>();
    ArrayList<TLRPC.TL_topPeer> inlineBots=DataQuery.getInstance(currentAccount).inlineBots;
    if (!usernameOnly && needBotContext && dogPostion == 0 && !inlineBots.isEmpty()) {
      int count=0;
      for (int a=0; a < inlineBots.size(); a++) {
        TLRPC.User user=messagesController.getUser(inlineBots.get(a).peer.user_id);
        if (user == null) {
          continue;
        }
        if (user.username != null && user.username.length() > 0 && (usernameString.length() > 0 && user.username.toLowerCase().startsWith(usernameString) || usernameString.length() == 0)) {
          newResult.add(user);
          newResultsHashMap.put(user.id,user);
          count++;
        }
        if (count == 5) {
          break;
        }
      }
    }
    final TLRPC.Chat chat;
    if (parentFragment != null) {
      chat=parentFragment.getCurrentChat();
    }
 else     if (info != null) {
      chat=messagesController.getChat(info.id);
    }
 else {
      chat=null;
    }
    if (chat != null && info != null && info.participants != null && (!ChatObject.isChannel(chat) || chat.megagroup)) {
      for (int a=0; a < info.participants.participants.size(); a++) {
        TLRPC.ChatParticipant chatParticipant=info.participants.participants.get(a);
        TLRPC.User user=messagesController.getUser(chatParticipant.user_id);
        if (user == null || !usernameOnly && UserObject.isUserSelf(user) || newResultsHashMap.indexOfKey(user.id) >= 0) {
          continue;
        }
        if (usernameString.length() == 0) {
          if (!user.deleted) {
            newResult.add(user);
          }
        }
 else {
          if (user.username != null && user.username.length() > 0 && user.username.toLowerCase().startsWith(usernameString)) {
            newResult.add(user);
            newMap.put(user.id,user);
          }
 else {
            if (user.first_name != null && user.first_name.length() > 0 && user.first_name.toLowerCase().startsWith(usernameString)) {
              newResult.add(user);
              newMap.put(user.id,user);
            }
 else             if (user.last_name != null && user.last_name.length() > 0 && user.last_name.toLowerCase().startsWith(usernameString)) {
              newResult.add(user);
              newMap.put(user.id,user);
            }
 else             if (hasSpace && ContactsController.formatName(user.first_name,user.last_name).toLowerCase().startsWith(usernameString)) {
              newResult.add(user);
              newMap.put(user.id,user);
            }
          }
        }
      }
    }
    Collections.sort(newResult,(lhs,rhs) -> {
      if (newMap.indexOfKey(lhs.id) >= 0 && newMap.indexOfKey(rhs.id) >= 0) {
        return 0;
      }
 else       if (newMap.indexOfKey(lhs.id) >= 0) {
        return -1;
      }
 else       if (newMap.indexOfKey(rhs.id) >= 0) {
        return 1;
      }
      int lhsNum=users.indexOf(lhs.id);
      int rhsNum=users.indexOf(rhs.id);
      if (lhsNum != -1 && rhsNum != -1) {
        return lhsNum < rhsNum ? -1 : (lhsNum == rhsNum ? 0 : 1);
      }
 else       if (lhsNum != -1 && rhsNum == -1) {
        return -1;
      }
 else       if (lhsNum == -1 && rhsNum != -1) {
        return 1;
      }
      return 0;
    }
);
    searchResultHashtags=null;
    searchResultCommands=null;
    searchResultCommandsHelp=null;
    searchResultCommandsUsers=null;
    searchResultSuggestions=null;
    if (chat != null && chat.megagroup && usernameString.length() > 0) {
      if (newResult.size() < 5) {
        AndroidUtilities.runOnUIThread(cancelDelayRunnable=() -> {
          cancelDelayRunnable=null;
          showUsersResult(newResult,newMap,true);
        }
,1000);
      }
 else {
        showUsersResult(newResult,newMap,true);
      }
      AndroidUtilities.runOnUIThread(searchGlobalRunnable=new Runnable(){
        @Override public void run(){
          if (searchGlobalRunnable != this) {
            return;
          }
          TLRPC.TL_channels_getParticipants req=new TLRPC.TL_channels_getParticipants();
          req.channel=MessagesController.getInputChannel(chat);
          req.limit=20;
          req.offset=0;
          TLRPC.TL_channelParticipantsSearch channelParticipantsSearch=new TLRPC.TL_channelParticipantsSearch();
          channelParticipantsSearch.q=usernameString;
          req.filter=channelParticipantsSearch;
          final int currentReqId=++channelLastReqId;
          channelReqId=ConnectionsManager.getInstance(currentAccount).sendRequest(req,(response,error) -> AndroidUtilities.runOnUIThread(() -> {
            if (channelReqId != 0 && currentReqId == channelLastReqId && searchResultUsernamesMap != null && searchResultUsernames != null) {
              showUsersResult(newResult,newMap,false);
              if (error == null) {
                TLRPC.TL_channels_channelParticipants res=(TLRPC.TL_channels_channelParticipants)response;
                messagesController.putUsers(res.users,false);
                boolean hasResults=!searchResultUsernames.isEmpty();
                if (!res.participants.isEmpty()) {
                  int currentUserId=UserConfig.getInstance(currentAccount).getClientUserId();
                  for (int a=0; a < res.participants.size(); a++) {
                    TLRPC.ChannelParticipant participant=res.participants.get(a);
                    if (searchResultUsernamesMap.indexOfKey(participant.user_id) >= 0 || !isSearchingMentions && participant.user_id == currentUserId) {
                      continue;
                    }
                    TLRPC.User user=messagesController.getUser(participant.user_id);
                    if (user == null) {
                      return;
                    }
                    searchResultUsernames.add(user);
                  }
                }
              }
              notifyDataSetChanged();
              delegate.needChangePanelVisibility(!searchResultUsernames.isEmpty());
            }
            channelReqId=0;
          }
));
        }
      }
,200);
    }
 else {
      showUsersResult(newResult,newMap,true);
    }
  }
 else   if (foundType == 1) {
    ArrayList<String> newResult=new ArrayList<>();
    String hashtagString=result.toString().toLowerCase();
    ArrayList<SearchAdapterHelper.HashtagObject> hashtags=searchAdapterHelper.getHashtags();
    for (int a=0; a < hashtags.size(); a++) {
      SearchAdapterHelper.HashtagObject hashtagObject=hashtags.get(a);
      if (hashtagObject != null && hashtagObject.hashtag != null && hashtagObject.hashtag.startsWith(hashtagString)) {
        newResult.add(hashtagObject.hashtag);
      }
    }
    searchResultHashtags=newResult;
    searchResultUsernames=null;
    searchResultUsernamesMap=null;
    searchResultCommands=null;
    searchResultCommandsHelp=null;
    searchResultCommandsUsers=null;
    searchResultSuggestions=null;
    notifyDataSetChanged();
    delegate.needChangePanelVisibility(!newResult.isEmpty());
  }
 else   if (foundType == 2) {
    ArrayList<String> newResult=new ArrayList<>();
    ArrayList<String> newResultHelp=new ArrayList<>();
    ArrayList<TLRPC.User> newResultUsers=new ArrayList<>();
    String command=result.toString().toLowerCase();
    for (int b=0; b < botInfo.size(); b++) {
      TLRPC.BotInfo info=botInfo.valueAt(b);
      for (int a=0; a < info.commands.size(); a++) {
        TLRPC.TL_botCommand botCommand=info.commands.get(a);
        if (botCommand != null && botCommand.command != null && botCommand.command.startsWith(command)) {
          newResult.add("/" + botCommand.command);
          newResultHelp.add(botCommand.description);
          newResultUsers.add(messagesController.getUser(info.user_id));
        }
      }
    }
    searchResultHashtags=null;
    searchResultUsernames=null;
    searchResultUsernamesMap=null;
    searchResultSuggestions=null;
    searchResultCommands=newResult;
    searchResultCommandsHelp=newResultHelp;
    searchResultCommandsUsers=newResultUsers;
    notifyDataSetChanged();
    delegate.needChangePanelVisibility(!newResult.isEmpty());
  }
 else   if (foundType == 3) {
    String[] newLanguage=AndroidUtilities.getCurrentKeyboardLanguage();
    if (!Arrays.equals(newLanguage,lastSearchKeyboardLanguage)) {
      DataQuery.getInstance(currentAccount).fetchNewEmojiKeywords(newLanguage);
    }
    lastSearchKeyboardLanguage=newLanguage;
    DataQuery.getInstance(currentAccount).getEmojiSuggestions(lastSearchKeyboardLanguage,result.toString(),false,(param,alias) -> {
      searchResultSuggestions=param;
      searchResultHashtags=null;
      searchResultUsernames=null;
      searchResultUsernamesMap=null;
      searchResultCommands=null;
      searchResultCommandsHelp=null;
      searchResultCommandsUsers=null;
      notifyDataSetChanged();
      delegate.needChangePanelVisibility(searchResultSuggestions != null && !searchResultSuggestions.isEmpty());
    }
);
  }
}
