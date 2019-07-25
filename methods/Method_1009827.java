void clean(){
  mSharedPreferences.edit().remove(TAG_SINGLE_TOP).remove(TAG_SINGLE_INSTANCE).remove(TAG_SINGLE_TASK).apply();
}
