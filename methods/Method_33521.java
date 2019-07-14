public static UserDataBase getDatabase(){
  if (sInstance == null) {
    sInstance=Room.databaseBuilder(CloudReaderApplication.getInstance(),UserDataBase.class,"User.db").addMigrations(MIGRATION_1_2).build();
  }
  return sInstance;
}
