protected boolean noNeedToRemoveDuplicate(Request request){
  return HttpConstant.Method.POST.equalsIgnoreCase(request.getMethod());
}
