/** 
 * Returns array of all  {@link Folder}s as  {@code String}s. You can use these names in {@link #useFolder(String)} method.
 * @return array of all {@link Folder}s as  {@code String}s.
 */
public String[] getAllFolders(){
  final Folder[] folders;
  try {
    folders=getService().getDefaultFolder().list("*");
  }
 catch (  final MessagingException msgexc) {
    throw new MailException("Failed to connect to folder",msgexc);
  }
  final String[] folderNames=new String[folders.length];
  for (int i=0; i < folders.length; i++) {
    final Folder folder=folders[i];
    folderNames[i]=folder.getFullName();
  }
  return folderNames;
}
