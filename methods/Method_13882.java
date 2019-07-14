static public Fusiontables getFusionTablesService(String token){
  Credential credential=new GoogleCredential().setAccessToken(token);
  Fusiontables fusiontables=new Fusiontables.Builder(GoogleAPIExtension.HTTP_TRANSPORT,GoogleAPIExtension.JSON_FACTORY,credential).setApplicationName(GoogleAPIExtension.SERVICE_APP_NAME).build();
  ;
  return fusiontables;
}
