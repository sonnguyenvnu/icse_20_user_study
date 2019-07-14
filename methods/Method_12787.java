protected ActivityManagerProxy createActivityManagerProxy(IActivityManager origin) throws Exception {
  return new ActivityManagerProxy(this,origin);
}
