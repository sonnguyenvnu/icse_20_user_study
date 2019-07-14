/** 
 * Sets the possible options for the C++ tokenizer.
 * @param properties the properties
 * @see #OPTION_SKIP_BLOCKS
 * @see #OPTION_SKIP_BLOCKS_PATTERN
 */
public void setProperties(Properties properties){
  skipBlocks=Boolean.parseBoolean(properties.getProperty(OPTION_SKIP_BLOCKS,Boolean.TRUE.toString()));
  if (skipBlocks) {
    String skipBlocksPattern=properties.getProperty(OPTION_SKIP_BLOCKS_PATTERN,DEFAULT_SKIP_BLOCKS_PATTERN);
    String[] split=skipBlocksPattern.split("\\|",2);
    skipBlocksStart=split[0];
    if (split.length == 1) {
      skipBlocksEnd=split[0];
    }
 else {
      skipBlocksEnd=split[1];
    }
  }
}
