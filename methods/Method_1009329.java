@Override public boolean matches(PropertyNameInfo propertyNameInfo){
  List<Tokens> sourceTokens=propertyNameInfo.getSourcePropertyTokens();
  List<Tokens> destTokens=propertyNameInfo.getDestinationPropertyTokens();
  if (sourceTokens.size() != destTokens.size())   return false;
  for (int propIndex=0; propIndex < destTokens.size(); propIndex++) {
    Tokens sTokens=sourceTokens.get(propIndex);
    Tokens dTokens=destTokens.get(propIndex);
    if (sTokens.size() != dTokens.size())     return false;
    for (int tokenIndex=0; tokenIndex < sTokens.size(); tokenIndex++)     if (!sTokens.token(tokenIndex).equalsIgnoreCase(dTokens.token(tokenIndex)))     return false;
  }
  return true;
}
