/** 
 * ????????
 * @param params ????
 * @param certPath ??????
 * @param certPass ????
 * @return {String}
 */
public static String batchquerycomment(Map<String,String> params,String certPath,String certPassword){
  return doPostSSL(BATCH_QUERY_COMMENT_URL,params,certPath,certPassword);
}
