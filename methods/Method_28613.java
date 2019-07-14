public void configGet(final byte[] pattern){
  sendCommand(CONFIG,Keyword.GET.raw,pattern);
}
