@Override public void load(Path configPath,ActionEvent... actionEvent){
  fadeOut(infoLabel,"Loading...");
  List<String> aceThemeList=new LinkedList<>(IOHelper.readAllLines(getConfigDirectory().resolve("ace_themes.txt")));
  List<String> languageList=this.languageList();
  Reader fileReader=IOHelper.fileReader(configPath);
  JsonReader jsonReader=Json.createReader(fileReader);
  JsonObject jsonObject=jsonReader.readObject();
  setMainStagePositions(jsonObject);
  setDetachedStagePositions(jsonObject);
  int fontSize=jsonObject.getInt("fontSize",14);
  String aceTheme=jsonObject.getString("aceTheme","xcode");
  String defaultLanguage=jsonObject.getString("defaultLanguage","en");
  boolean useWrapMode=jsonObject.getBoolean("useWrapMode",true);
  boolean showGutter=jsonObject.getBoolean("showGutter",false);
  boolean detachedPreview=jsonObject.getBoolean("detachedPreview",false);
  boolean showHiddenFiles=jsonObject.getBoolean("showHiddenFiles",false);
  boolean newInstall=jsonObject.getBoolean("newInstall",true);
  int wrapLimit=jsonObject.getInt("wrapLimit",0);
  boolean autoUpdate=jsonObject.getBoolean("autoUpdate",true);
  final boolean validateDocbook=jsonObject.getBoolean("validateDocbook",false);
  String clipboardImageFilePattern=jsonObject.getString("clipboardImageFilePattern","'Image'-ddMMyy-hhmmss.SSS'.png'");
  String foldStyle=jsonObject.getString("foldStyle","default");
  int hangFileSizeLimit=jsonObject.getInt("hangFileSizeLimit",3);
  String editorTheme=jsonObject.getString("editorTheme");
  Stream<Path> themeStream=IOHelper.walk(getConfigDirectory().resolve("themes"),Integer.MAX_VALUE);
  List<Path> themeConfigs=themeStream.filter(p -> p.endsWith("theme.json")).collect(Collectors.toList());
  List<Theme> themeList=new LinkedList<>();
  for (  Path themeConfig : themeConfigs) {
    try (JsonReader themeReader=Json.createReader(IOHelper.fileReader(themeConfig))){
      Theme theme=new Theme(themeReader.readObject());
      if (theme.isEnabled()) {
        theme.setConfigPath(themeConfig);
        themeList.add(theme);
      }
    }
   }
  Optional<Theme> themeOptional=themeList.stream().filter(e -> e.getThemeName().equals(editorTheme)).findFirst();
  themeOptional.ifPresent(t -> {
    themeList.remove(t);
    themeList.add(0,t);
  }
);
  aceThemeList.remove(aceTheme);
  aceThemeList.add(0,aceTheme);
  languageList.remove(defaultLanguage);
  languageList.add(0,defaultLanguage);
  IOHelper.close(jsonReader,fileReader);
  threadService.runActionLater(() -> {
    getEditorTheme().setAll(themeList);
    getAceTheme().setAll(aceThemeList);
    getDefaultLanguage().setAll(languageList);
    this.setFontSize(fontSize);
    this.setUseWrapMode(useWrapMode);
    this.setShowGutter(showGutter);
    this.setDetachedPreview(detachedPreview);
    this.setWrapLimit(wrapLimit);
    this.setAutoUpdate(autoUpdate);
    this.setShowHiddenFiles(showHiddenFiles);
    this.setValidateDocbook(validateDocbook);
    this.setClipboardImageFilePattern(clipboardImageFilePattern);
    this.setHangFileSizeLimit(hangFileSizeLimit);
    if (Objects.isNull(getNewInstall())) {
      this.setNewInstall(newInstall);
    }
    if (FoldStyle.contains(foldStyle)) {
      this.setFoldStyle(FoldStyle.valueOf(foldStyle));
    }
    if (jsonObject.containsKey("scrollSpeed")) {
      this.setScrollSpeed(jsonObject.getJsonNumber("scrollSpeed").doubleValue());
    }
    if (jsonObject.containsKey("firstSplitter")) {
      JsonNumber firstSplitter=jsonObject.getJsonNumber("firstSplitter");
      this.setFirstSplitter(firstSplitter.doubleValue());
    }
    if (jsonObject.containsKey("secondSplitter")) {
      JsonNumber secondSplitter=jsonObject.getJsonNumber("secondSplitter");
      this.setSecondSplitter(secondSplitter.doubleValue());
    }
    if (jsonObject.containsKey("verticalSplitter")) {
      JsonNumber secondSplitter=jsonObject.getJsonNumber("verticalSplitter");
      this.setVerticalSplitter(secondSplitter.doubleValue());
    }
    fadeOut(infoLabel,"Loaded...");
  }
);
}
