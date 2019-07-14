/** 
 * @param assetPath Path where `server-config.json` lives.
 * @param assetManager Asset manager to use to load `server-config.json`.
 * @return A string representation of the config JSON.
 */
private @NonNull String configJSONString(final @NonNull String assetPath,final @NonNull AssetManager assetManager){
  try {
    final InputStream input;
    input=assetManager.open(assetPath);
    final byte[] buffer=new byte[input.available()];
    input.read(buffer);
    input.close();
    return new String(buffer);
  }
 catch (  final IOException e) {
    Timber.e(e.getMessage());
  }
  return "{}";
}
