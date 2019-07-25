@Override public Settings copy(){
  cfg.size();
  return new HadoopSettings(new Configuration(cfg));
}
