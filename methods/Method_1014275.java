@Override public String execute(String request,int connectTimeout,int readTimeout){
  String response=null;
  HttpsURLConnection connection=null;
  try {
    String correctedRequest=checkSessionToken(request);
    connection=getConnection(correctedRequest,connectTimeout,readTimeout);
    if (connection != null) {
      connection.connect();
      final int responseCode=connection.getResponseCode();
      if (responseCode != HttpURLConnection.HTTP_FORBIDDEN) {
        if (responseCode == HttpURLConnection.HTTP_INTERNAL_ERROR) {
          response=IOUtils.toString(connection.getErrorStream());
        }
 else {
          response=IOUtils.toString(connection.getInputStream());
        }
        if (response != null) {
          if (!response.contains("Authentication failed")) {
            if (loginCounter > 0) {
              connectionManager.checkConnection(responseCode);
            }
            loginCounter=0;
          }
 else {
            connectionManager.checkConnection(ConnectionManager.AUTHENTIFICATION_PROBLEM);
            loginCounter++;
          }
        }
      }
      connection.disconnect();
      if (response == null && connectionManager != null && loginCounter <= MAY_A_NEW_SESSION_TOKEN_IS_NEEDED) {
        if (responseCode == HttpURLConnection.HTTP_FORBIDDEN) {
          execute(addSessionToken(correctedRequest,connectionManager.getNewSessionToken()),connectTimeout,readTimeout);
          loginCounter++;
        }
 else {
          connectionManager.checkConnection(responseCode);
          loginCounter++;
          return null;
        }
      }
      return response;
    }
  }
 catch (  SocketTimeoutException e) {
    informConnectionManager(ConnectionManager.SOCKET_TIMEOUT_EXCEPTION);
  }
catch (  java.net.ConnectException e) {
    informConnectionManager(ConnectionManager.CONNECTION_EXCEPTION);
  }
catch (  MalformedURLException e) {
    informConnectionManager(ConnectionManager.MALFORMED_URL_EXCEPTION);
  }
catch (  java.net.UnknownHostException e) {
    informConnectionManager(ConnectionManager.UNKNOWN_HOST_EXCEPTION);
  }
catch (  IOException e) {
    logger.error("An IOException occurred: ",e);
    if (connectionManager != null) {
      informConnectionManager(ConnectionManager.GENERAL_EXCEPTION);
    }
  }
 finally {
    if (connection != null) {
      connection.disconnect();
    }
  }
  return null;
}
