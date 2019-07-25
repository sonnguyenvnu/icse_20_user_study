/** 
 * Generates a dex file and returns its bytes.
 */
public byte[] generate(){
  if (outputDex == null) {
    DexOptions options=new DexOptions();
    options.minSdkVersion=DexFormat.API_NO_EXTENDED_OPCODES;
    outputDex=new DexFile(options);
  }
  for (  TypeDeclaration typeDeclaration : types.values()) {
    outputDex.add(typeDeclaration.toClassDefItem());
  }
  try {
    return outputDex.toDex(null,false);
  }
 catch (  IOException e) {
    throw new RuntimeException(e);
  }
}
