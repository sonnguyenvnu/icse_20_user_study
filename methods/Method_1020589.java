/** 
 * Construct a OkReplay Response based on the provided OkHttp response 
 */
static Response adapt(final okhttp3.Response okhttpResponse,ResponseBody body){
  return new RecordedResponse.Builder().headers(okhttpResponse.headers()).body(body).protocol(okhttpResponse.protocol()).code(okhttpResponse.code()).build();
}
