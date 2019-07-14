private static String buildMessage(Type mockedClass,TypeSymbol forbiddenType){
  return String.format("Do not mock '%s'%s",mockedClass,(mockedClass.asElement().equals(forbiddenType) ? "" : " (which is-a '" + forbiddenType + "')"));
}
