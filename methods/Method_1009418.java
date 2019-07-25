@Override public void load(Path configPath,ActionEvent... actionEvent){
  fadeOut(infoLabel,"Loading...");
  loadPathDefaults();
  Reader fileReader=IOHelper.fileReader(configPath);
  JsonReader jsonReader=Json.createReader(fileReader);
  JsonObject jsonObject=jsonReader.readObject();
  String stylesheetDefault=jsonObject.getString("stylesheetDefault",null);
  String stylesheetOverrides=jsonObject.getString("stylesheetOverrides",null);
  String mathjax=jsonObject.getString("mathjax",null);
  String kindlegen=jsonObject.getString("kindlegen",null);
  IOHelper.close(jsonReader,fileReader);
  threadService.runActionLater(() -> {
    if (Objects.nonNull(stylesheetDefault)) {
      this.setStylesheetDefault(stylesheetDefault);
    }
    if (Objects.nonNull(stylesheetOverrides)) {
      this.setStylesheetOverrides(stylesheetOverrides);
    }
    if (Objects.nonNull(mathjax)) {
      this.setMathjax(mathjax);
    }
    if (Objects.nonNull(kindlegen)) {
      this.setKindlegen(kindlegen);
    }
    fadeOut(infoLabel,"Loaded...");
  }
);
}
