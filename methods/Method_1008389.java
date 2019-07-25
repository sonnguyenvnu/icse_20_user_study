/** 
 * Write the keystore to the given config directory. 
 */
public void save(Path configDir) throws Exception {
  assert isLoaded();
  char[] password=this.keystorePassword.get().getPassword();
  SimpleFSDirectory directory=new SimpleFSDirectory(configDir);
  String tmpFile=KEYSTORE_FILENAME + ".tmp";
  try (IndexOutput output=directory.createOutput(tmpFile,IOContext.DEFAULT)){
    CodecUtil.writeHeader(output,KEYSTORE_FILENAME,FORMAT_VERSION);
    output.writeByte(password.length == 0 ? (byte)0 : (byte)1);
    output.writeString(NEW_KEYSTORE_TYPE);
    output.writeString(NEW_KEYSTORE_STRING_KEY_ALGO);
    output.writeString(NEW_KEYSTORE_FILE_KEY_ALGO);
    output.writeMapOfStrings(settingTypes.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,e -> e.getValue().name())));
    assert type.equals(NEW_KEYSTORE_TYPE) : "keystore type changed";
    assert stringFactory.getAlgorithm().equals(NEW_KEYSTORE_STRING_KEY_ALGO) : "string pbe algo changed";
    assert fileFactory.getAlgorithm().equals(NEW_KEYSTORE_FILE_KEY_ALGO) : "file pbe algo changed";
    ByteArrayOutputStream keystoreBytesStream=new ByteArrayOutputStream();
    keystore.get().store(keystoreBytesStream,password);
    byte[] keystoreBytes=keystoreBytesStream.toByteArray();
    output.writeInt(keystoreBytes.length);
    output.writeBytes(keystoreBytes,keystoreBytes.length);
    CodecUtil.writeFooter(output);
  }
 catch (  final AccessDeniedException e) {
    final String message=String.format(Locale.ROOT,"unable to create temporary keystore at [%s], please check filesystem permissions",configDir.resolve(tmpFile));
    throw new UserException(ExitCodes.CONFIG,message,e);
  }
  Path keystoreFile=keystorePath(configDir);
  Files.move(configDir.resolve(tmpFile),keystoreFile,StandardCopyOption.REPLACE_EXISTING,StandardCopyOption.ATOMIC_MOVE);
  PosixFileAttributeView attrs=Files.getFileAttributeView(keystoreFile,PosixFileAttributeView.class);
  if (attrs != null) {
    attrs.setPermissions(PosixFilePermissions.fromString("rw-rw----"));
  }
}
