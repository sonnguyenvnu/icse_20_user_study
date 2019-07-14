public static ModifiableConfiguration prefixView(ConfigNamespace newRoot,ConfigNamespace prefixRoot,ModifiableHadoopConfiguration mc){
  HadoopConfiguration prefixConf=new HadoopConfiguration(mc.getHadoopConfiguration(),ConfigElement.getPath(prefixRoot,true) + ".");
  return new ModifiableConfiguration(newRoot,prefixConf,Restriction.NONE);
}
