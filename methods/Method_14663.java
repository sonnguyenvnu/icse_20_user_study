public void setCustomMetadata(String key,Serializable value){
  if (value == null) {
    _customMetadata.remove(key);
  }
 else {
    _customMetadata.put(key,value);
  }
  updateModified();
}
