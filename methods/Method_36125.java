@Override public String proxyHostHeader(){
  return optionSet.hasArgument(PROXY_ALL) ? URI.create((String)optionSet.valueOf(PROXY_ALL)).getAuthority() : null;
}
