private void sendLogs(){
  if (getParentActivity() == null) {
    return;
  }
  AlertDialog progressDialog=new AlertDialog(getParentActivity(),3);
  progressDialog.setCanCacnel(false);
  progressDialog.show();
  Utilities.globalQueue.postRunnable(() -> {
    try {
      File sdCard=ApplicationLoader.applicationContext.getExternalFilesDir(null);
      File dir=new File(sdCard.getAbsolutePath() + "/logs");
      File zipFile=new File(dir,"logs.zip");
      if (zipFile.exists()) {
        zipFile.delete();
      }
      File[] files=dir.listFiles();
      boolean[] finished=new boolean[1];
      BufferedInputStream origin=null;
      ZipOutputStream out=null;
      try {
        FileOutputStream dest=new FileOutputStream(zipFile);
        out=new ZipOutputStream(new BufferedOutputStream(dest));
        byte[] data=new byte[1024 * 64];
        for (int i=0; i < files.length; i++) {
          FileInputStream fi=new FileInputStream(files[i]);
          origin=new BufferedInputStream(fi,data.length);
          ZipEntry entry=new ZipEntry(files[i].getName());
          out.putNextEntry(entry);
          int count;
          while ((count=origin.read(data,0,data.length)) != -1) {
            out.write(data,0,count);
          }
          if (origin != null) {
            origin.close();
            origin=null;
          }
        }
        finished[0]=true;
      }
 catch (      Exception e) {
        e.printStackTrace();
      }
 finally {
        if (origin != null) {
          origin.close();
        }
        if (out != null) {
          out.close();
        }
      }
      AndroidUtilities.runOnUIThread(() -> {
        try {
          progressDialog.dismiss();
        }
 catch (        Exception ignore) {
        }
        if (finished[0]) {
          Uri uri;
          if (Build.VERSION.SDK_INT >= 24) {
            uri=FileProvider.getUriForFile(getParentActivity(),BuildConfig.APPLICATION_ID + ".provider",zipFile);
          }
 else {
            uri=Uri.fromFile(zipFile);
          }
          Intent i=new Intent(Intent.ACTION_SEND);
          if (Build.VERSION.SDK_INT >= 24) {
            i.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
          }
          i.setType("message/rfc822");
          i.putExtra(Intent.EXTRA_EMAIL,"");
          i.putExtra(Intent.EXTRA_SUBJECT,"Logs from " + LocaleController.getInstance().formatterStats.format(System.currentTimeMillis()));
          i.putExtra(Intent.EXTRA_STREAM,uri);
          getParentActivity().startActivityForResult(Intent.createChooser(i,"Select email application."),500);
        }
 else {
          Toast.makeText(getParentActivity(),LocaleController.getString("ErrorOccurred",R.string.ErrorOccurred),Toast.LENGTH_SHORT).show();
        }
      }
);
    }
 catch (    Exception e) {
      e.printStackTrace();
    }
  }
);
}
