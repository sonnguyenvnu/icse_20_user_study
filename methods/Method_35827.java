public static RequestFilterAction continueWith(Request request){
  return new ContinueAction(request);
}
