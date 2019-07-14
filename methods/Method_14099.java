static protected String resolve(ButterflyModule module,String path){
  MountPoint mountPoint=module.getMountPoint();
  if (mountPoint != null) {
    String mountPointPath=mountPoint.getMountPoint();
    if (mountPointPath != null) {
      StringBuffer sb=new StringBuffer();
      boolean slashed=path.startsWith("/");
      char[] mountPointChars=mountPointPath.toCharArray();
      sb.append(mountPointChars,0,slashed ? mountPointChars.length - 1 : mountPointChars.length);
      sb.append(path);
      return sb.toString();
    }
  }
  return null;
}
