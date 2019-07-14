public String getRoleDesc(){
  if (type == ConstUtils.CACHE_REDIS_SENTINEL) {
    return "sentinel";
  }
 else {
    return roleDesc;
  }
}
