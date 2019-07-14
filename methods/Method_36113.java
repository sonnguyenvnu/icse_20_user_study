private void validate(){
  if (optionSet.has(HTTPS_KEYSTORE) && !optionSet.has(HTTPS_PORT)) {
    throw new IllegalArgumentException("HTTPS port number must be specified if specifying the keystore path");
  }
  if (optionSet.has(RECORD_MAPPINGS) && optionSet.has(DISABLE_REQUEST_JOURNAL)) {
    throw new IllegalArgumentException("Request journal must be enabled to record stubs");
  }
}
