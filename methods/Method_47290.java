/** 
 * Checks whether cloud account of certain type is present or not
 * @param serviceType the {@link OpenMode} of account to check
 * @return the index of account, -1 if not found
 */
public synchronized int containsAccounts(OpenMode serviceType){
  int i=0;
  for (  CloudStorage storage : accounts) {
switch (serviceType) {
case BOX:
      if (storage instanceof Box)       return i;
    break;
case DROPBOX:
  if (storage instanceof Dropbox)   return i;
break;
case GDRIVE:
if (storage instanceof GoogleDrive) return i;
break;
case ONEDRIVE:
if (storage instanceof OneDrive) return i;
break;
default :
return -1;
}
i++;
}
return -1;
}
