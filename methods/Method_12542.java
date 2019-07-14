@SuppressWarnings("unchecked") private static Map<String,Object> convertHttptrace(Map<String,Object> in){
  Map<String,Object> out=new LinkedHashMap<>();
  out.put("timestamp",getInstant(in.get("timestamp")));
  Map<String,Object> in_info=(Map<String,Object>)in.get("info");
  if (in_info != null) {
    Map<String,Object> request=new LinkedHashMap<>();
    request.put("method",in_info.get("method"));
    request.put("uri",in_info.get("path"));
    out.put("request",request);
    Map<String,Object> response=new LinkedHashMap<>();
    Map<String,Object> in_headers=(Map<String,Object>)in_info.get("headers");
    if (in_headers != null) {
      Map<String,Object> in_request_headers=(Map<String,Object>)in_headers.get("request");
      if (in_request_headers != null) {
        Map<String,Object> requestHeaders=new LinkedHashMap<>();
        in_request_headers.forEach((k,v) -> requestHeaders.put(k,singletonList(v)));
        request.put("headers",requestHeaders);
      }
      Map<String,Object> in_response_headers=(Map<String,Object>)in_headers.get("response");
      if (in_response_headers != null) {
        if (in_response_headers.get("status") instanceof String) {
          response.put("status",Long.valueOf(in_response_headers.get("status").toString()));
        }
        Map<String,Object> responseHeaders=new LinkedHashMap<>();
        in_response_headers.forEach((k,v) -> responseHeaders.put(k,singletonList(v)));
        responseHeaders.remove("status");
        response.put("headers",responseHeaders);
      }
    }
    out.put("response",response);
    if (in_info.get("timeTaken") instanceof String) {
      out.put("timeTaken",Long.valueOf(in_info.get("timeTaken").toString()));
    }
  }
  return out;
}
