private String buildMessage(Type mockedClass,TypeSymbol forbiddenType,T doNotMock){
  return buildMessage(mockedClass,forbiddenType,null,doNotMock);
}
