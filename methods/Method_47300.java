public synchronized CloudStorage getAccount(OpenMode serviceType){
  for (  CloudStorage storage : accounts) {
switch (serviceType) {
case BOX:
      if (storage instanceof Box)       return storage;
    break;
case DROPBOX:
  if (storage instanceof Dropbox)   return storage;
break;
case GDRIVE:
if (storage instanceof GoogleDrive) return storage;
break;
case ONEDRIVE:
if (storage instanceof OneDrive) return storage;
break;
default :
return null;
}
}
return null;
}
