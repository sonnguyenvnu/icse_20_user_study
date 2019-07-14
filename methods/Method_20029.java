private Task<String> addMessage(String text){
  Map<String,Object> data=new HashMap<>();
  data.put("text",text);
  data.put("push",true);
  return mFunctions.getHttpsCallable("addMessage").call(data).continueWith(new Continuation<HttpsCallableResult,String>(){
    @Override public String then(    @NonNull Task<HttpsCallableResult> task) throws Exception {
      String result=(String)task.getResult().getData();
      return result;
    }
  }
);
}
