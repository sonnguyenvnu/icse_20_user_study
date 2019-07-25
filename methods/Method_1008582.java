private Path total(){
  Path res=new Path();
  Set<String> seenDevices=new HashSet<>(paths.length);
  for (  Path subPath : paths) {
    if (subPath.path != null) {
      if (!seenDevices.add(subPath.path)) {
        continue;
      }
    }
    res.add(subPath);
  }
  return res;
}
