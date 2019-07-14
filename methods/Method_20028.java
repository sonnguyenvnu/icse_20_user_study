private Task<Integer> addNumbers(int a,int b){
  Map<String,Object> data=new HashMap<>();
  data.put("firstNumber",a);
  data.put("secondNumber",b);
  return mFunctions.getHttpsCallable("addNumbers").call(data).continueWith(new Continuation<HttpsCallableResult,Integer>(){
    @Override public Integer then(    @NonNull Task<HttpsCallableResult> task) throws Exception {
      Map<String,Object> result=(Map<String,Object>)task.getResult().getData();
      return (Integer)result.get("operationResult");
    }
  }
);
}
