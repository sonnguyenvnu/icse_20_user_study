private void addDubboProtocolsPortMetadata(Map<String,String> metadata){
  allExportedURLs.values().stream().flatMap(v -> v.stream()).forEach(url -> {
    String protocol=url.getProtocol();
    String propertyName=getDubboProtocolPropertyName(protocol);
    String propertyValue=valueOf(url.getPort());
    metadata.put(propertyName,propertyValue);
  }
);
}
