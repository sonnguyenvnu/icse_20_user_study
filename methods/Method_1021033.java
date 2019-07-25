protected void compare(){
  if (!inited) {
    for (    Map.Entry<String,TimeSize> entry : curScan.entrySet()) {
      onChange(ACTION_INIT,entry.getKey());
    }
    inited=true;
    return;
  }
  for (  Map.Entry<String,TimeSize> entry : curScan.entrySet()) {
    if (preScan.get(entry.getKey()) == null)     onChange(ACTION_ADD,entry.getKey());
  }
  for (  Map.Entry<String,TimeSize> entry : preScan.entrySet()) {
    if (curScan.get(entry.getKey()) == null)     onChange(ACTION_DELETE,entry.getKey());
  }
  for (  Map.Entry<String,TimeSize> entry : curScan.entrySet()) {
    TimeSize pre=preScan.get(entry.getKey());
    if (pre != null && !pre.equals(entry.getValue()))     onChange(ACTION_UPDATE,entry.getKey());
  }
}
