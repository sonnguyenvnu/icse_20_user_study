@Override public void load(Path configPath,ActionEvent... actionEvent){
  Reader fileReader=IOHelper.fileReader(configPath);
  JsonReader jsonReader=Json.createReader(fileReader);
  JsonObject jsonObject=jsonReader.readObject();
  JsonArray recentFiles=jsonObject.getJsonArray("recentFiles");
  JsonArray favoriteDirectories=jsonObject.getJsonArray("favoriteDirectories");
  String workingDirectory=jsonObject.getString("workingDirectory",System.getProperty("user.home"));
  IOHelper.close(jsonReader,fileReader);
  threadService.runActionLater(() -> {
    if (Objects.nonNull(workingDirectory)) {
      this.workingDirectory.setValue(workingDirectory);
    }
    if (Objects.nonNull(recentFiles)) {
      recentFiles.stream().map(e -> (JsonString)e).map(e -> e.getString()).map(e -> new Item(IOHelper.getPath(e))).forEach(this.recentFiles::add);
    }
    if (Objects.nonNull(favoriteDirectories)) {
      favoriteDirectories.stream().map(e -> (JsonString)e).map(e -> e.getString()).forEach(this.favoriteDirectories::add);
    }
  }
);
}
