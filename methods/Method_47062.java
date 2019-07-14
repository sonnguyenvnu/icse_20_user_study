private static File installTemporaryTrack(final Context context) throws IOException {
  final File externalFilesDir=getExternalFilesDir(context);
  if (externalFilesDir == null) {
    return null;
  }
  final File temporaryTrack=new File(externalFilesDir,"temptrack.mp3");
  if (!temporaryTrack.exists()) {
    InputStream in=null;
    OutputStream out=null;
    try {
      in=context.getResources().openRawResource(R.raw.temptrack);
      out=new FileOutputStream(temporaryTrack);
      final byte[] buffer=new byte[4096];
      int bytesRead;
      while ((bytesRead=in.read(buffer)) != -1) {
        out.write(buffer,0,bytesRead);
      }
    }
  finally {
      out.close();
      in.close();
    }
  }
  return temporaryTrack;
}
