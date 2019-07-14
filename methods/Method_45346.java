private HttpsArg httpsArg(final CommandLine cmd){
  String https=cmd.getOptionValue("https");
  String keystore=cmd.getOptionValue("keystore");
  String cert=cmd.getOptionValue("cert");
  if (https != null) {
    if (keystore == null || cert == null) {
      throw new ParseArgException("keystore and cert must be set for HTTPS");
    }
    return new HttpsArg(https,keystore,cert);
  }
  throw new ParseArgException("HTTPS arguments are expected");
}
