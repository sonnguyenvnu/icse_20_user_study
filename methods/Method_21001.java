public @NonNull String newPledgeUrl(){
  return Uri.parse(secureWebProjectUrl()).buildUpon().appendEncodedPath("pledge/new").toString();
}
