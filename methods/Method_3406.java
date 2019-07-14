/** 
 * ????
 * @param modelFile
 * @throws IOException
 */
public void load(String modelFile) throws IOException {
  if (HanLP.Config.DEBUG)   logger.start("?? %s ... ",modelFile);
  ByteArrayStream byteArray=ByteArrayStream.createByteArrayStream(modelFile);
  if (!load(byteArray)) {
    throw new IOException(String.format("%s ????",modelFile));
  }
  if (HanLP.Config.DEBUG)   logger.finish(" ????\n");
}
