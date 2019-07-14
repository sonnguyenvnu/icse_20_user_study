protected String getUrlForHelpType(final @HelpType int helpType){
  final Uri.Builder builder=Uri.parse(this.webEndpoint).buildUpon();
switch (helpType) {
case HELP_TYPE_TERMS:
    builder.appendEncodedPath(TERMS_OF_USE);
  break;
case HELP_TYPE_PRIVACY:
builder.appendEncodedPath(PRIVACY);
break;
case HELP_TYPE_HOW_IT_WORKS:
builder.appendEncodedPath(HELLO);
break;
case HELP_TYPE_COOKIE_POLICY:
builder.appendEncodedPath(COOKIES);
break;
}
return builder.toString();
}
