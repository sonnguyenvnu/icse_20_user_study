public @NonNull String editPledgeUrl(){
  return Uri.parse(secureWebProjectUrl()).buildUpon().appendEncodedPath("pledge/edit").toString();
}
