/** 
 * ????????
 * @param e
 * @return
 */
public static JSONObject newErrorResult(Exception e){
  if (e != null) {
    e.printStackTrace();
    int code;
    if (e instanceof UnsupportedEncodingException) {
      code=JSONResponse.CODE_UNSUPPORTED_ENCODING;
    }
 else     if (e instanceof IllegalAccessException) {
      code=JSONResponse.CODE_ILLEGAL_ACCESS;
    }
 else     if (e instanceof UnsupportedOperationException) {
      code=JSONResponse.CODE_UNSUPPORTED_OPERATION;
    }
 else     if (e instanceof NotExistException) {
      code=JSONResponse.CODE_NOT_FOUND;
    }
 else     if (e instanceof IllegalArgumentException) {
      code=JSONResponse.CODE_ILLEGAL_ARGUMENT;
    }
 else     if (e instanceof NotLoggedInException) {
      code=JSONResponse.CODE_NOT_LOGGED_IN;
    }
 else     if (e instanceof TimeoutException) {
      code=JSONResponse.CODE_TIME_OUT;
    }
 else     if (e instanceof ConflictException) {
      code=JSONResponse.CODE_CONFLICT;
    }
 else     if (e instanceof ConditionErrorException) {
      code=JSONResponse.CODE_CONDITION_ERROR;
    }
 else     if (e instanceof UnsupportedDataTypeException) {
      code=JSONResponse.CODE_UNSUPPORTED_TYPE;
    }
 else     if (e instanceof OutOfRangeException) {
      code=JSONResponse.CODE_OUT_OF_RANGE;
    }
 else     if (e instanceof NullPointerException) {
      code=JSONResponse.CODE_NULL_POINTER;
    }
 else {
      code=JSONResponse.CODE_SERVER_ERROR;
    }
    return newResult(code,e.getMessage());
  }
  return newResult(JSONResponse.CODE_SERVER_ERROR,JSONResponse.MSG_SERVER_ERROR);
}
