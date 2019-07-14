private void startShareOnTwitter(final @NonNull Project project){
  new TweetComposer.Builder(context()).text(shareString(project)).uri(Uri.parse(project.webProjectUrl())).show();
}
