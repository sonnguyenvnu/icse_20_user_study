/** 
 * Serializes this action into an  {@link OutputStream}.
 * @param output The stream to write to.
 */
public final void serializeToStream(OutputStream output) throws IOException {
  DataOutputStream dataOutputStream=new DataOutputStream(output);
  dataOutputStream.writeUTF(type);
  dataOutputStream.writeInt(VERSION);
  dataOutputStream.writeUTF(uri.toString());
  dataOutputStream.writeBoolean(isRemoveAction);
  dataOutputStream.writeInt(data.length);
  dataOutputStream.write(data);
  dataOutputStream.writeInt(keys.size());
  for (int i=0; i < keys.size(); i++) {
    StreamKey key=keys.get(i);
    dataOutputStream.writeInt(key.periodIndex);
    dataOutputStream.writeInt(key.groupIndex);
    dataOutputStream.writeInt(key.trackIndex);
  }
  dataOutputStream.writeBoolean(customCacheKey != null);
  if (customCacheKey != null) {
    dataOutputStream.writeUTF(customCacheKey);
  }
  dataOutputStream.flush();
}
