public static HttpClient getInstance(){
  return getInstance(ConfigurationContext.getInstance().getHttpClientConfiguration());
}
