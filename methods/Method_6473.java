public String getFileName(){
  if (location != null) {
    return location.volume_id + "_" + location.local_id + "." + ext;
  }
 else {
    return Utilities.MD5(webFile.url) + "." + ext;
  }
}
