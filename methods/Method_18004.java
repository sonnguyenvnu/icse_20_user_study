/** 
 * @return the "most base" Context of this Context, i.e. the Activity, Application, or Servicebacking this Context and all its ContextWrappers. In some cases, e.g. instrumentation tests or other places we don't wrap a standard Context, this root Context may instead be a raw ContextImpl.
 */
static Context getRootContext(Context context){
  Context currentContext=context;
  while (currentContext instanceof ContextWrapper && !(currentContext instanceof Activity) && !(currentContext instanceof Application) && !(currentContext instanceof Service)) {
    currentContext=((ContextWrapper)currentContext).getBaseContext();
  }
  return currentContext;
}
