private void hydrate(ByteBuf bytes) throws Exception {
  if (bytes.readableBytes() > 0) {
    try {
      SerializedForm deserialized=defaultSerializer.deserialize(SerializedForm.class,new ByteBufInputStream(bytes));
      if (deserialized == null) {
        this.entries=new HashMap<>();
      }
 else {
        entries=deserialized.entries;
      }
    }
 catch (    Exception e) {
      LOGGER.warn("Exception thrown deserializing session " + getId() + " with serializer " + defaultSerializer + " (session will be discarded)",e);
      this.entries=new HashMap<>();
      markDirty();
    }
  }
 else {
    this.entries=new HashMap<>();
  }
}
