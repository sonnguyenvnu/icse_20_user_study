public boolean enable(){
  return (settings.getAsBoolean("application.api.qps.enable",false));
}
