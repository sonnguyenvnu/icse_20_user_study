@Override public void doInBackground(){
  filename=null;
  try {
    File dir=system.getFilesDir("Backups");
    if (dir == null)     return;
    filename=DatabaseUtils.saveDatabaseCopy(context,dir);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
}
