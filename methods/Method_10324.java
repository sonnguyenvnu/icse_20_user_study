public void updateRequestHeaders(HttpUriRequest uriRequest){
  if (file.exists() && file.canWrite())   current=file.length();
  if (current > 0) {
    append=true;
    uriRequest.setHeader("Range","bytes=" + current + "-");
  }
}
