@Override public boolean isDuplicate(Request request,Task task){
  return !urls.add(getUrl(request));
}
