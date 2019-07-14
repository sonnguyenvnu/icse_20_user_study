@OnError static void onError(ComponentContext c,Exception error){
  ErrorBoundary.updateErrorSync(c,error);
}
