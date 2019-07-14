private int httpsPortNumber(){
  return optionSet.has(HTTPS_PORT) ? Integer.parseInt((String)optionSet.valueOf(HTTPS_PORT)) : -1;
}
