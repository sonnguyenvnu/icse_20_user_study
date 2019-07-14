@Override public WriteConfiguration copy(){
  return new HadoopConfiguration(new Configuration(config),prefix);
}
