@Nonnull private Collection<FSFile> findFiles(@Nonnull String path) throws FileNotFoundException {
  File searchRoot=new File(root,util.simplifyPath(path));
  if (searchRoot.exists() && searchRoot.isFile()) {
    throw new FileNotFoundException(format("expecting a directory at %s, instead found a file",path));
  }
  Collection<FSFile> foundFiles=new ArrayList<>();
  BreadthFirstFileTreeIterator iterator=new BreadthFirstFileTreeIterator(searchRoot);
  while (iterator.hasNext()) {
    File file=(File)iterator.next();
    foundFiles.add(files.getUnchecked(util.simplifyPath(file.getPath().replaceFirst(root.getPath(),""))));
  }
  return foundFiles;
}
