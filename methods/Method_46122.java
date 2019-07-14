protected void registerAppInfoOnce(String appName){
synchronized (MeshRegistry.class) {
    if (!registedApp) {
      ApplicationInfoRequest applicationInfoRequest=buildApplicationRequest(appName);
      boolean registed=client.registeApplication(applicationInfoRequest);
      if (!registed) {
        throw new RuntimeException("registe application occors error," + applicationInfoRequest);
      }
 else {
        registedApp=true;
      }
    }
  }
}
