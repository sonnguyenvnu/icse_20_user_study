/** 
 * Renames <code>indexFolderName</code> index folders found in node paths and custom path iff  {@link #needsUpgrade(Index,String)} is true.Index folder in custom paths are renamed first followed by index folders in each node path.
 */
void upgrade(final String indexFolderName) throws IOException {
  for (  NodeEnvironment.NodePath nodePath : nodeEnv.nodePaths()) {
    final Path indexFolderPath=nodePath.indicesPath.resolve(indexFolderName);
    final IndexMetaData indexMetaData=IndexMetaData.FORMAT.loadLatestState(logger,NamedXContentRegistry.EMPTY,indexFolderPath);
    if (indexMetaData != null) {
      final Index index=indexMetaData.getIndex();
      if (needsUpgrade(index,indexFolderName)) {
        logger.info("{} upgrading [{}] to new naming convention",index,indexFolderPath);
        final IndexSettings indexSettings=new IndexSettings(indexMetaData,settings);
        if (indexSettings.hasCustomDataPath()) {
          final Path customLocationSource=nodeEnv.resolveBaseCustomLocation(indexSettings).resolve(indexFolderName);
          final Path customLocationTarget=customLocationSource.resolveSibling(index.getUUID());
          if (Files.exists(customLocationSource) && Files.exists(customLocationTarget) == false) {
            upgrade(index,customLocationSource,customLocationTarget);
          }
 else {
            logger.info("[{}] no upgrade needed - already upgraded",customLocationTarget);
          }
        }
        upgrade(index,indexFolderPath,indexFolderPath.resolveSibling(index.getUUID()));
      }
 else {
        logger.debug("[{}] no upgrade needed - already upgraded",indexFolderPath);
      }
    }
 else {
      logger.warn("[{}] no index state found - ignoring",indexFolderPath);
    }
  }
}
