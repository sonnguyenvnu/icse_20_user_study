/** 
 * Creates a new random  {@link #uuid}. These are used to uniquely identify the server to renderers (i.e. renderers treat multiple servers with the same UUID as the same server).
 * @return {@link String} with an Universally Unique Identifier.
 */
public synchronized String usn(){
  if (uuid == null) {
    uuid=getConfiguration().getUuid();
    if (uuid == null) {
      uuid=UUID.randomUUID().toString();
      LOGGER.info("Generated new random UUID: {}",uuid);
      getConfiguration().setUuid(uuid);
      try {
        getConfiguration().save();
      }
 catch (      ConfigurationException e) {
        LOGGER.error("Failed to save configuration with new UUID",e);
      }
    }
    LOGGER.info("Using the following UUID configured in UMS.conf: {}",uuid);
  }
  return "uuid:" + uuid;
}
