private static String withoutAdminRoot(String url){
  return url.replace(ADMIN_CONTEXT_ROOT,"");
}
