@Override public TableStruct tableStruct(String table,Supplier<TableStruct,SQLException> structSupplier) throws SQLException {
  String tableStructKey=table + ".struct";
  if (attachmentCache.containsKey(tableStructKey)) {
    log.debug("cache hit! table {}'s struct.",table);
    return attachmentCache.attachment(tableStructKey);
  }
  TableStruct tableStruct=structSupplier.get();
  if (Objects.nonNull(primaryKeysProviders)) {
    primaryKeysProviders.forEach(primaryKeysProvider -> {
      List<String> users=primaryKeysProvider.provide().get(table);
      if (Objects.nonNull(users)) {
        List<String> primaryKes=tableStruct.getPrimaryKeys();
        primaryKes.addAll(users.stream().filter(key -> !primaryKes.contains(key)).filter(key -> tableStruct.getColumns().keySet().contains(key)).collect(Collectors.toList()));
        tableStruct.setPrimaryKeys(primaryKes);
      }
    }
);
  }
  attachmentCache.attach(tableStructKey,tableStruct);
  return tableStruct;
}
