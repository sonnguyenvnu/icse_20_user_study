public ByteBuf _XXXXX_(long ledgerId,long entryId,long location) throws IOException, Bookie.NoEntryException {
  ByteBuf data=internalReadEntry(ledgerId,entryId,location);
  return data;
}