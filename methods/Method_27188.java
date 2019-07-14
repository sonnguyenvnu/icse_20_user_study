public void loadDevice(){
  DeviceName.with(App.getInstance()).request((info,error) -> {
    if (error == null && null != info) {
      deviceName=info.marketName;
    }
  }
);
}
