private void read(){
  if (index < tokens.length) {
    currentToken=tokens[index++];
    firstChar=currentToken.charAt(0);
  }
 else {
    currentToken="";
    firstChar=0;
  }
}
