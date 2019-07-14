@Override public void onDowngrade(SQLiteDatabase db,int oldVersion,int newVersion){
  onUpgrade(db,oldVersion,newVersion);
}
