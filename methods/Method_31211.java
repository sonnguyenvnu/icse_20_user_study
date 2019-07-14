private void doBaseline(SchemaHistory schemaHistory,CallbackExecutor callbackExecutor){
  new DbBaseline(schemaHistory,configuration.getBaselineVersion(),configuration.getBaselineDescription(),callbackExecutor).baseline();
}
