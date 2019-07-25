public static boolean contains(Path root,Path subPath){
  if (root == null || subPath == null)   return false;
  Iterator<Path> realPathIterator=root.normalize().iterator();
  Iterator<Path> subPathIterator=subPath.normalize().iterator();
  while (realPathIterator.hasNext()) {
    Path realPathSegment=realPathIterator.next();
    if (subPathIterator.hasNext()) {
      Path subPathSegment=subPathIterator.next();
      if (!Objects.equals(realPathSegment,subPathSegment)) {
        subPathIterator=subPath.normalize().iterator();
      }
    }
 else {
      break;
    }
  }
  return !subPathIterator.hasNext();
}
