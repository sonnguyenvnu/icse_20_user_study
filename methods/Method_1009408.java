@Override public void load(Path configPath,ActionEvent... actionEvent){
  infoLabel.setText("Loading...");
  Reader fileReader=IOHelper.fileReader(configPath);
  JsonReader jsonReader=Json.createReader(fileReader);
  JsonObject jsonObject=jsonReader.readObject();
  String safe=jsonObject.getString("safe","safe");
  boolean sourcemap=jsonObject.getBoolean("sourcemap",false);
  String backend=jsonObject.getString("backend",null);
  boolean header_footer=jsonObject.getBoolean("header_footer",false);
  JsonObject attributes=jsonObject.getJsonObject("attributes");
  String jsPlatform=jsonObject.getString("jsPlatform","Webkit");
  IOHelper.close(jsonReader,fileReader);
  threadService.runActionLater(() -> {
    this.setSafe(safe);
    this.sourcemap.set(sourcemap);
    this.setBackend(backend);
    this.setHeader_footer(header_footer);
    this.setJsPlatform(JSPlatform.valueOf(JSPlatform.class,jsPlatform));
    ObservableList<AttributesTable> attrList=FXCollections.observableArrayList();
    if (Objects.nonNull(attributes)) {
      for (      Map.Entry<String,JsonValue> attr : attributes.entrySet()) {
        AttributesTable attributesTable=new AttributesTable();
        attributesTable.setAttribute(attr.getKey());
        attributesTable.setValue(((JsonString)attr.getValue()).getString());
        attrList.add(attributesTable);
      }
    }
    setAttributes(attrList);
    fadeOut(infoLabel,"Loaded...");
  }
);
}
