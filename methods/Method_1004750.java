public String prettify(String error,ByteSequence body){
  if (internalVersion.onOrAfter(EsMajorVersion.V_2_X)) {
    return error;
  }
  String message=ErrorUtils.extractJsonParse(error,body);
  return (message != null ? error + "; fragment[" + message + "]" : error);
}
