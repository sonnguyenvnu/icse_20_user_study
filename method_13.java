public static String _XXXXX_(TopologyBaseAPIEntity entity){
  return new HashCodeBuilder().append(entity.getTags().get(TopologyConstants.SITE_TAG)).append(entity.getTags().get(TopologyConstants.HOSTNAME_TAG)).append(entity.getTags().get(TopologyConstants.ROLE_TAG)).build().toString();
}