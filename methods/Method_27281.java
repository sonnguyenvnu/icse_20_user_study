public static Observable<String> getChangelog(@NonNull Context context){
  return Observable.fromCallable(() -> {
    try (InputStream is=context.getResources().openRawResource(R.raw.changelog)){
      try (ByteArrayOutputStream byteStream=new ByteArrayOutputStream()){
        byte[] buffer=new byte[is.available()];
        int read=is.read(buffer);
        byteStream.write(buffer);
        return byteStream.toString();
      }
 catch (      IOException e) {
        e.printStackTrace();
      }
    }
     return null;
  }
);
}
