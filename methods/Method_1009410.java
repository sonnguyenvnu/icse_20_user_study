@Override public void load(Path configPath,ActionEvent... actionEvent){
  fadeOut(infoLabel,"Loading...");
  Reader fileReader=IOHelper.fileReader(configPath);
  JsonReader jsonReader=Json.createReader(fileReader);
  JsonObject jsonObject=jsonReader.readObject();
  Integer defaultImageScale=jsonObject.getInt("defaultImageScale",this.defaultImageScale.getValue());
  Integer defaultImageZoom=jsonObject.getInt("defaultImageZoom",this.defaultImageZoom.getValue());
  Integer defaultImageDpi=jsonObject.getInt("defaultImageDpi",this.defaultImageDpi.getValue());
  IOHelper.close(jsonReader,fileReader);
  threadService.runActionLater(() -> {
    this.setDefaultImageScale(defaultImageScale);
    this.setDefaultImageZoom(defaultImageZoom);
    this.setDefaultImageDpi(defaultImageDpi);
    fadeOut(infoLabel,"Loaded...");
  }
);
}
