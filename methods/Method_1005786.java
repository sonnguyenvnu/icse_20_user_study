public static void symlink(Path link,Path target){
  try {
    LOG.info("Creating symlink {} -> {}",link,target);
    symlinkCreator.createSymbolicLink(link,target);
  }
 catch (  IOException e) {
    LOG.error("Could not create symlink {} -> {}",link,target);
    throw new IllegalStateException(e);
  }
}
