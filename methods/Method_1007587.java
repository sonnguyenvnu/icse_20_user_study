private void migrate(String file,String name){
  File fromDir=new File(".",file);
  File toDir=new File(getWorkingDirectory(),file);
  if (fromDir.exists() & !toDir.exists()) {
    if (fromDir.renameTo(toDir)) {
      plugin.getLogger().info("Migrated " + name + " folder '" + file + "' from server root to plugin data folder.");
    }
 else {
      plugin.getLogger().warning("Error while migrating " + name + " folder!");
    }
  }
}
