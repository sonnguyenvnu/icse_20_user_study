/** 
 * Initializes session. When not specified (i.e. is <code>null</code>), session is fetched from session provider.
 */
protected void initSession(final DbSession session){
  if (session != null) {
    this.session=session;
    return;
  }
  final DbSessionProvider dbSessionProvider=dbOom.sessionProvider();
  this.session=dbSessionProvider.getDbSession();
}
