private void onAddMessageClicked(){
  String inputMessage=mMessageInputField.getText().toString();
  if (TextUtils.isEmpty(inputMessage)) {
    showSnackbar("Please enter a message.");
    return;
  }
  addMessage(inputMessage).addOnCompleteListener(new OnCompleteListener<String>(){
    @Override public void onComplete(    @NonNull Task<String> task){
      if (!task.isSuccessful()) {
        Exception e=task.getException();
        if (e instanceof FirebaseFunctionsException) {
          FirebaseFunctionsException ffe=(FirebaseFunctionsException)e;
          FirebaseFunctionsException.Code code=ffe.getCode();
          Object details=ffe.getDetails();
        }
        Log.w(TAG,"addMessage:onFailure",e);
        showSnackbar("An error occurred.");
        return;
      }
      String result=task.getResult();
      mMessageOutputField.setText(result);
    }
  }
);
}
