@Override @Activate protected void activate(@Nullable Map<String,@Nullable Object> configProperties){
  super.activate(configProperties);
  usbSerialDiscovery.registerDiscoveryListener(this);
}
