@Override public List<ConversationSearchResult> search(String keyword){
  return ChatManager.Instance().searchConversation(keyword,Arrays.asList(Single,Group),Arrays.asList(0,1));
}
