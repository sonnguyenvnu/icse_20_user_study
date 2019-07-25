/** 
 * Get a data set for an error response, with extra error information.
 * @param errorCode The error code.
 * @param extraData Additional data to return with the error.
 * @return The data to return as the result of the request.
 */
protected static Map<ReturnableData,Object> error(final ErrorCode errorCode,final Map<ReturnableData,Object> extraData){
  final Map<ReturnableData,Object> data=error(errorCode);
  data.putAll(extraData);
  return data;
}
