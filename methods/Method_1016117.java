/** 
 * {@inheritDoc}
 */
@Override public boolean accept(final File file){
  return GlobalConstants.IMAGE_FORMATS.contains(FileUtils.getFileExtPart(file.getName().toLowerCase(),false));
}
