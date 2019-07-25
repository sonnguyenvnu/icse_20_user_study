@JsonCreator static EncryptionConfig create(@JsonProperty("AutoLockManagers") final Boolean autoLockManagers){
  return builder().autoLockManagers(autoLockManagers).build();
}
