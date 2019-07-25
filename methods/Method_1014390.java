/** 
 * Updates the internal value state with the given binary payload.
 * @param data The binary payload to update the internal value.
 * @exception IllegalArgumentException Thrown if for example a text is assigned to a number type.
 */
public void update(byte data[]) throws IllegalArgumentException {
  String mimeType=null;
  if (data.length >= 2 && data[0] == (byte)0xFF && data[1] == (byte)0xD8 && data[data.length - 2] == (byte)0xFF && data[data.length - 1] == (byte)0xD9) {
    mimeType="image/jpeg";
  }
 else {
    try (final ByteArrayInputStream input=new ByteArrayInputStream(data)){
      try {
        mimeType=URLConnection.guessContentTypeFromStream(input);
      }
 catch (      final IOException ignored) {
      }
    }
 catch (    final IOException ignored) {
    }
  }
  state=new RawType(data,mimeType == null ? RawType.DEFAULT_MIME_TYPE : mimeType);
}
