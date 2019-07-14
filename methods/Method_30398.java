private static Intent makeClassIntent(String className){
  return new Intent().setComponent(new ComponentName(FRODO_PACKAGE_NAME,FRODO_PACKAGE_NAME + className));
}
