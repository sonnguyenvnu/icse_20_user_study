private void setFieldsWithPrefix(Properties props,String prefix){
  if (notNull(props,prefix,DEBUG)) {
    setDebug(getBoolean(props,prefix,DEBUG));
  }
  if (notNull(props,prefix,USER)) {
    setUser(getString(props,prefix,USER));
  }
  if (notNull(props,prefix,PASSWORD)) {
    setPassword(getString(props,prefix,PASSWORD));
  }
  if (notNull(props,prefix,HTTP_PRETTY_DEBUG)) {
    setPrettyDebugEnabled(getBoolean(props,prefix,HTTP_PRETTY_DEBUG));
  }
  if (notNull(props,prefix,HTTP_GZIP)) {
    setGZIPEnabled(getBoolean(props,prefix,HTTP_GZIP));
  }
  if (notNull(props,prefix,HTTP_PROXY_HOST)) {
    setHttpProxyHost(getString(props,prefix,HTTP_PROXY_HOST));
  }
 else   if (notNull(props,prefix,HTTP_PROXY_HOST_FALLBACK)) {
    setHttpProxyHost(getString(props,prefix,HTTP_PROXY_HOST_FALLBACK));
  }
  if (notNull(props,prefix,HTTP_PROXY_USER)) {
    setHttpProxyUser(getString(props,prefix,HTTP_PROXY_USER));
  }
  if (notNull(props,prefix,HTTP_PROXY_PASSWORD)) {
    setHttpProxyPassword(getString(props,prefix,HTTP_PROXY_PASSWORD));
  }
  if (notNull(props,prefix,HTTP_PROXY_PORT)) {
    setHttpProxyPort(getIntProperty(props,prefix,HTTP_PROXY_PORT));
  }
 else   if (notNull(props,prefix,HTTP_PROXY_PORT_FALLBACK)) {
    setHttpProxyPort(getIntProperty(props,prefix,HTTP_PROXY_PORT_FALLBACK));
  }
  if (notNull(props,prefix,HTTP_CONNECTION_TIMEOUT)) {
    setHttpConnectionTimeout(getIntProperty(props,prefix,HTTP_CONNECTION_TIMEOUT));
  }
  if (notNull(props,prefix,HTTP_READ_TIMEOUT)) {
    setHttpReadTimeout(getIntProperty(props,prefix,HTTP_READ_TIMEOUT));
  }
  if (notNull(props,prefix,HTTP_STREAMING_READ_TIMEOUT)) {
    setHttpStreamingReadTimeout(getIntProperty(props,prefix,HTTP_STREAMING_READ_TIMEOUT));
  }
  if (notNull(props,prefix,HTTP_RETRY_COUNT)) {
    setHttpRetryCount(getIntProperty(props,prefix,HTTP_RETRY_COUNT));
  }
  if (notNull(props,prefix,HTTP_RETRY_INTERVAL_SECS)) {
    setHttpRetryIntervalSeconds(getIntProperty(props,prefix,HTTP_RETRY_INTERVAL_SECS));
  }
  if (notNull(props,prefix,OAUTH_CONSUMER_KEY)) {
    setOAuthConsumerKey(getString(props,prefix,OAUTH_CONSUMER_KEY));
  }
  if (notNull(props,prefix,OAUTH_CONSUMER_SECRET)) {
    setOAuthConsumerSecret(getString(props,prefix,OAUTH_CONSUMER_SECRET));
  }
  if (notNull(props,prefix,OAUTH_ACCESS_TOKEN)) {
    setOAuthAccessToken(getString(props,prefix,OAUTH_ACCESS_TOKEN));
  }
  if (notNull(props,prefix,OAUTH_ACCESS_TOKEN_SECRET)) {
    setOAuthAccessTokenSecret(getString(props,prefix,OAUTH_ACCESS_TOKEN_SECRET));
  }
  if (notNull(props,prefix,OAUTH2_TOKEN_TYPE)) {
    setOAuth2TokenType(getString(props,prefix,OAUTH2_TOKEN_TYPE));
  }
  if (notNull(props,prefix,OAUTH2_ACCESS_TOKEN)) {
    setOAuth2AccessToken(getString(props,prefix,OAUTH2_ACCESS_TOKEN));
  }
  if (notNull(props,prefix,OAUTH2_SCOPE)) {
    setOAuth2Scope(getString(props,prefix,OAUTH2_SCOPE));
  }
  if (notNull(props,prefix,ASYNC_NUM_THREADS)) {
    setAsyncNumThreads(getIntProperty(props,prefix,ASYNC_NUM_THREADS));
  }
  if (notNull(props,prefix,ASYNC_DAEMON_ENABLED)) {
    setDaemonEnabled(getBoolean(props,prefix,ASYNC_DAEMON_ENABLED));
  }
  if (notNull(props,prefix,STREAM_THREAD_NAME)) {
    setStreamThreadName(getString(props,prefix,STREAM_THREAD_NAME));
  }
  if (notNull(props,prefix,CONTRIBUTING_TO)) {
    setContributingTo(getLongProperty(props,prefix,CONTRIBUTING_TO));
  }
  if (notNull(props,prefix,ASYNC_DISPATCHER_IMPL)) {
    setDispatcherImpl(getString(props,prefix,ASYNC_DISPATCHER_IMPL));
  }
  if (notNull(props,prefix,OAUTH_REQUEST_TOKEN_URL)) {
    setOAuthRequestTokenURL(getString(props,prefix,OAUTH_REQUEST_TOKEN_URL));
  }
  if (notNull(props,prefix,OAUTH_AUTHORIZATION_URL)) {
    setOAuthAuthorizationURL(getString(props,prefix,OAUTH_AUTHORIZATION_URL));
  }
  if (notNull(props,prefix,OAUTH_ACCESS_TOKEN_URL)) {
    setOAuthAccessTokenURL(getString(props,prefix,OAUTH_ACCESS_TOKEN_URL));
  }
  if (notNull(props,prefix,OAUTH_AUTHENTICATION_URL)) {
    setOAuthAuthenticationURL(getString(props,prefix,OAUTH_AUTHENTICATION_URL));
  }
  if (notNull(props,prefix,OAUTH2_TOKEN_URL)) {
    setOAuth2TokenURL(getString(props,prefix,OAUTH2_TOKEN_URL));
  }
  if (notNull(props,prefix,OAUTH2_INVALIDATE_TOKEN_URL)) {
    setOAuth2InvalidateTokenURL(getString(props,prefix,OAUTH2_INVALIDATE_TOKEN_URL));
  }
  if (notNull(props,prefix,REST_BASE_URL)) {
    setRestBaseURL(getString(props,prefix,REST_BASE_URL));
  }
  if (notNull(props,prefix,STREAM_BASE_URL)) {
    setStreamBaseURL(getString(props,prefix,STREAM_BASE_URL));
  }
  if (notNull(props,prefix,USER_STREAM_BASE_URL)) {
    setUserStreamBaseURL(getString(props,prefix,USER_STREAM_BASE_URL));
  }
  if (notNull(props,prefix,SITE_STREAM_BASE_URL)) {
    setSiteStreamBaseURL(getString(props,prefix,SITE_STREAM_BASE_URL));
  }
  if (notNull(props,prefix,INCLUDE_MY_RETWEET)) {
    setIncludeMyRetweetEnabled(getBoolean(props,prefix,INCLUDE_MY_RETWEET));
  }
  if (notNull(props,prefix,INCLUDE_ENTITIES)) {
    setIncludeEntitiesEnabled(getBoolean(props,prefix,INCLUDE_ENTITIES));
  }
  if (notNull(props,prefix,INCLUDE_EMAIL)) {
    setIncludeEmailEnabled(getBoolean(props,prefix,INCLUDE_EMAIL));
  }
  if (notNull(props,prefix,INCLUDE_EXT_ALT_TEXT)) {
    setIncludeExtAltTextEnabled(getBoolean(props,prefix,INCLUDE_EXT_ALT_TEXT));
  }
  if (notNull(props,prefix,TWEET_MODE_EXTENDED)) {
    setTweetModeExtended(getBoolean(props,prefix,TWEET_MODE_EXTENDED));
  }
  if (notNull(props,prefix,LOGGER_FACTORY)) {
    setLoggerFactory(getString(props,prefix,LOGGER_FACTORY));
  }
  if (notNull(props,prefix,JSON_STORE_ENABLED)) {
    setJSONStoreEnabled(getBoolean(props,prefix,JSON_STORE_ENABLED));
  }
  if (notNull(props,prefix,MBEAN_ENABLED)) {
    setMBeanEnabled(getBoolean(props,prefix,MBEAN_ENABLED));
  }
  if (notNull(props,prefix,STREAM_USER_REPLIES_ALL)) {
    setUserStreamRepliesAllEnabled(getBoolean(props,prefix,STREAM_USER_REPLIES_ALL));
  }
  if (notNull(props,prefix,STREAM_USER_WITH_FOLLOWINGS)) {
    setUserStreamWithFollowingsEnabled(getBoolean(props,prefix,STREAM_USER_WITH_FOLLOWINGS));
  }
  if (notNull(props,prefix,STREAM_STALL_WARNINGS_ENABLED)) {
    setStallWarningsEnabled(getBoolean(props,prefix,STREAM_STALL_WARNINGS_ENABLED));
  }
  if (notNull(props,prefix,APPLICATION_ONLY_AUTH_ENABLED)) {
    setApplicationOnlyAuthEnabled(getBoolean(props,prefix,APPLICATION_ONLY_AUTH_ENABLED));
  }
  if (notNull(props,prefix,MEDIA_PROVIDER)) {
    setMediaProvider(getString(props,prefix,MEDIA_PROVIDER));
  }
  if (notNull(props,prefix,MEDIA_PROVIDER_API_KEY)) {
    setMediaProviderAPIKey(getString(props,prefix,MEDIA_PROVIDER_API_KEY));
  }
  if (notNull(props,prefix,MEDIA_PROVIDER_PARAMETERS)) {
    String[] propsAry=getString(props,prefix,MEDIA_PROVIDER_PARAMETERS).split("&");
    Properties p=new Properties();
    for (    String str : propsAry) {
      String[] parameter=str.split("=");
      p.setProperty(parameter[0],parameter[1]);
    }
    setMediaProviderParameters(p);
  }
  cacheInstance();
}
