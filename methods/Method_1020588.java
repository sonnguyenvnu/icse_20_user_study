/** 
 * Construct a OkHttp Response from a previously recorded interaction 
 */
static okhttp3.Response adapt(okhttp3.Request okhttpRequest,Response recordedResponse){
  ResponseBody responseBody=ResponseBody.create(MediaType.parse(recordedResponse.getContentType()),recordedResponse.body());
  return new okhttp3.Response.Builder().headers(recordedResponse.headers()).body(responseBody).code(recordedResponse.code()).protocol(recordedResponse.protocol()).request(okhttpRequest).message("").build();
}
