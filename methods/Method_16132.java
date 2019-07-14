@PostConstruct public void init(){
  jta.forEach((id,config) -> {
    if (config.getId() == null) {
      config.setId(id);
    }
 else     if (!config.getId().equals(id)) {
      jta.put(config.getId(),config);
    }
  }
);
}
