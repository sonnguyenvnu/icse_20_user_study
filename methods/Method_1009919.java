@Override public void intercept(Chain chain) throws Exception {
  String uriStr=chain.request().uri.toString();
  Log.d("???????","uri = " + uriStr);
  chain.proceed(chain.request());
}
