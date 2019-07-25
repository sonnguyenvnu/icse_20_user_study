@Override public void save(@Nonnull final String path){
  options.setOption(KyloCatalogConstants.PATH_OPTION,path);
  save();
}
