private void init(TableDesc tableDesc,boolean read){
  Configuration cfg=getConf();
  Settings settings=HadoopSettingsManager.loadFrom(cfg);
  if (read) {
  }
 else {
    HadoopCfgUtils.setOutputCommitterClass(cfg,EsOutputFormat.EsOutputCommitter.class.getName());
  }
  Assert.hasText(tableDesc.getProperties().getProperty(TABLE_LOCATION),String.format("no table location [%s] declared by Hive resulting in abnormal execution;",TABLE_LOCATION));
}
