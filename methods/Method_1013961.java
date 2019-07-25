protected void activate(@Nullable Map<String,@Nullable Object> properties){
  if (properties != null) {
    Object value=properties.get(AUTO_IGNORE_CONFIG_PROPERTY);
    autoIgnore=value == null || !value.toString().equals("false");
    value=properties.get(ALWAYS_AUTO_APPROVE_CONFIG_PROPERTY);
    alwaysAutoApprove=value != null && value.toString().equals("true");
    autoApproveInboxEntries();
  }
}
