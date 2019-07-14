public void searchForContextBotForNextOffset(){
  if (contextQueryReqid != 0 || nextQueryOffset == null || nextQueryOffset.length() == 0 || foundContextBot == null || searchingContextQuery == null) {
    return;
  }
  searchForContextBotResults(true,foundContextBot,searchingContextQuery,nextQueryOffset);
}
