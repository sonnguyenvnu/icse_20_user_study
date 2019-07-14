public Map<String,Object> loadSidebar() throws IOException {
  try (Reader reader=Files.newBufferedReader(sidebarPath,StandardCharsets.UTF_8)){
    Yaml yaml=new Yaml();
    @SuppressWarnings("unchecked") Map<String,Object> sidebar=(Map<String,Object>)yaml.load(reader);
    return sidebar;
  }
 }
