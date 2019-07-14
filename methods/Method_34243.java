private Object[] getFieldsForUpdate(ClientDetails clientDetails){
  String json=null;
  try {
    json=mapper.write(clientDetails.getAdditionalInformation());
  }
 catch (  Exception e) {
    logger.warn("Could not serialize additional information: " + clientDetails,e);
  }
  return new Object[]{clientDetails.getResourceIds() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getResourceIds()) : null,clientDetails.getScope() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getScope()) : null,clientDetails.getAuthorizedGrantTypes() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorizedGrantTypes()) : null,clientDetails.getRegisteredRedirectUri() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getRegisteredRedirectUri()) : null,clientDetails.getAuthorities() != null ? StringUtils.collectionToCommaDelimitedString(clientDetails.getAuthorities()) : null,clientDetails.getAccessTokenValiditySeconds(),clientDetails.getRefreshTokenValiditySeconds(),json,getAutoApproveScopes(clientDetails),clientDetails.getClientId()};
}
