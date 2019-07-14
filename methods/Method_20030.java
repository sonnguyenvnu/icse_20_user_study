private void onCalculateClicked(){
  int firstNumber;
  int secondNumber;
  hideKeyboard();
  try {
    firstNumber=Integer.parseInt(mFirstNumberField.getText().toString());
    secondNumber=Integer.parseInt(mSecondNumberField.getText().toString());
  }
 catch (  NumberFormatException e) {
    showSnackbar("Please enter two numbers.");
    return;
  }
  addNumbers(firstNumber,secondNumber).addOnCompleteListener(new OnCompleteListener<Integer>(){
    @Override public void onComplete(    @NonNull Task<Integer> task){
      if (!task.isSuccessful()) {
        Exception e=task.getException();
        if (e instanceof FirebaseFunctionsException) {
          FirebaseFunctionsException ffe=(FirebaseFunctionsException)e;
          FirebaseFunctionsException.Code code=ffe.getCode();
          Object details=ffe.getDetails();
        }
        Log.w(TAG,"addNumbers:onFailure",e);
        showSnackbar("An error occurred.");
        return;
      }
      Integer result=task.getResult();
      mAddResultField.setText(String.valueOf(result));
    }
  }
);
}
