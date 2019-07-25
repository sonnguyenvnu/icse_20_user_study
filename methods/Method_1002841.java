/** 
 * Returns disabled access log writer.
 */
static AccessLogWriter disabled(){
  return requestLog -> {
  }
;
}
