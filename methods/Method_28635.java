/** 
 * Return the UNIX time stamp of the last successfully saving of the dataset on disk. <p> Return the UNIX TIME of the last DB save executed with success. A client may check if a {@link #bgsave() BGSAVE} command succeeded reading the LASTSAVE value, then issuing a BGSAVEcommand and checking at regular intervals every N seconds if LASTSAVE changed.
 * @return Integer reply, specifically an UNIX time stamp.
 */
@Override public Long lastsave(){
  client.lastsave();
  return client.getIntegerReply();
}
