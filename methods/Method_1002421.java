@SuppressWarnings("unchecked") private void pop(){
  assert !_stack.isEmpty() : "Trying to pop empty stack at " + _jsonParser.getTokenLocation();
  DataComplex tmp=_stack.pop();
  if (_stack.isEmpty()) {
    _result=(T)tmp;
    _expectedTokens=0;
  }
 else {
    _isCurrList=_stack.peek() instanceof DataList;
    updateExpected();
  }
}
