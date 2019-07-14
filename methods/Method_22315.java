@Override public InputStream getInputStream(@NonNull Context context){
  return context.getResources().openRawResource(rawRes);
}
