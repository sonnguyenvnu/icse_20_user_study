/** 
 * Logs in to OpenSubtitles and stores the result in  {@link #token}. Some users might get a different API address in response, which will be reflected in the  {@link URL} returned by this method.<p> <b>All access to  {@link #token} must be protected by {@link #TOKEN_LOCK}</b>.
 * @param url The API {@link URL} to use for login.
 * @return The URL to use if the login was a success, {@code null} otherwise.
 */
private static URL login(){
  TOKEN_LOCK.writeLock().lock();
  try {
    if (token != null && token.isYoung()) {
      return token.isValid() ? token.getURL() : null;
    }
    LOGGER.debug("Trying to log in to OpenSubtitles");
    CredMgr.Credential credentials=PMS.getCred("opensubtitles");
    String pword="";
    String username="";
    if (credentials != null) {
      if (isNotBlank(credentials.password)) {
        pword=DigestUtils.md5Hex(credentials.password);
      }
      username=credentials.username;
    }
    URL url;
    try {
      url=new URL(OPENSUBS_URL);
    }
 catch (    MalformedURLException e) {
      throw new AssertionError("OpenSubtitles URL \"" + OPENSUBS_URL + "\" is invalid");
    }
    URLConnection urlConnection=url.openConnection();
    if (!(urlConnection instanceof HttpURLConnection)) {
      throw new OpenSubtitlesException("Invalid URL: " + OPENSUBS_URL);
    }
    HttpURLConnection connection=(HttpURLConnection)urlConnection;
    connection.setDoInput(true);
    connection.setDoOutput(true);
    connection.setRequestProperty("Content-Type","text/xml;charset=UTF-8");
    connection.setRequestMethod("POST");
    connection.setConnectTimeout(2000);
    Params params=new Params();
    params.add(new ValueString(username));
    params.add(new ValueString(pword));
    params.add(new ValueString(null));
    params.add(new ValueString(UA));
    try (OutputStream out=LOGGER.isTraceEnabled() ? new LoggableOutputStream(connection.getOutputStream(),StandardCharsets.UTF_8) : connection.getOutputStream()){
      XMLStreamWriter writer=createWriter(out);
      writeMethod(writer,"LogIn",params);
      writer.flush();
      if (out instanceof LoggableOutputStream) {
        LOGGER.trace("Sending OpenSubtitles login request:\n{}",toLogString((LoggableOutputStream)out));
      }
    }
 catch (    XMLStreamException|FactoryConfigurationError e) {
      LOGGER.error("An error occurred while generating OpenSubtitles login request: {}",e.getMessage());
      LOGGER.trace("",e);
    }
    params=null;
    try (InputStream reply=LOGGER.isTraceEnabled() ? new LoggableInputStream(sendXMLStream(connection,5,500),StandardCharsets.UTF_8) : sendXMLStream(connection,5,500)){
      LOGGER.trace("Parsing OpenSubtitles login response");
      XMLStreamReader reader=null;
      try {
        reader=createReader(reply);
        params=readMethodResponse(reader);
      }
  finally {
        if (reader != null) {
          reader.close();
        }
      }
      if (reply instanceof LoggableInputStream) {
        LOGGER.trace("Received OpenSubtitles login response:\n{}",toLogString((LoggableInputStream)reply));
      }
    }
     if (params == null) {
      LOGGER.error("Failed to parse Opensubtitles login response, aborting");
      token=Token.createInvalidToken();
      return null;
    }
    if (params.size() != 1 || !(params.get(0).getValue() instanceof Struct)) {
      LOGGER.error("Unexpected reply from OpenSubtitles:\n{}",params);
      token=Token.createInvalidToken();
      return null;
    }
    if (!checkStatus(params)) {
      token=Token.createInvalidToken();
      LOGGER.error("OpenSubtitles login was aborted");
      return null;
    }
    Struct members=(Struct)params.get(0).getValue();
    String tokenString;
    Member<?,?> member=members.get("token");
    if (!(member instanceof MemberString) || isBlank(((MemberString)member).getValue())) {
      LOGGER.error("Failed to parse OpenSubtitles login token: {}",member);
      token=Token.createInvalidToken();
      return null;
    }
    tokenString=((MemberString)member).getValue();
    User tokenUser=null;
    member=members.get("data");
    if (member != null) {
      tokenUser=User.createFromStruct((Struct)member.getValue());
    }
    token=new Token(tokenString,tokenUser,url);
    if (!token.isValid()) {
      LOGGER.error("Failed to log in to OpenSubtitles");
      return null;
    }
    if (LOGGER.isDebugEnabled()) {
      if (token.getUser() != null) {
        LOGGER.debug("Successfully logged in to OpenSubtitles as {}",token.getUser().getUserNickName());
      }
 else {
        LOGGER.debug("Successfully logged in to OpenSubtitles anonymously");
      }
    }
    return token.getURL();
  }
 catch (  XMLStreamException|IOException e) {
    LOGGER.error("An error occurred during OpenSubtitles login: {}",e.getMessage());
    LOGGER.trace("",e);
    token=Token.createInvalidToken();
    return null;
  }
 finally {
    TOKEN_LOCK.writeLock().unlock();
  }
}
