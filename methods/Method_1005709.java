private void setup(Class mHookClass){
  XposedHelpers.setStaticObjectField(mHookClass,FIELD_NAME_METHOD,mMember);
  XposedHelpers.setStaticObjectField(mHookClass,FIELD_NAME_BACKUP_METHOD,mBackupMethod);
  XposedHelpers.setStaticObjectField(mHookClass,FIELD_NAME_HOOK_INFO,mHookInfo);
}
