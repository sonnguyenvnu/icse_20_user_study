@NonNull @Override public Uri getAuthorizationUrl(){
  return new Uri.Builder().scheme("https").authority("github.com").appendPath("login").appendPath("oauth").appendPath("authorize").appendQueryParameter("client_id",GithubConfigHelper.getClientId()).appendQueryParameter("redirect_uri",GithubConfigHelper.getRedirectUrl()).appendQueryParameter("scope","user,repo,gist,notifications,read:org").appendQueryParameter("state",BuildConfig.APPLICATION_ID).build();
}
