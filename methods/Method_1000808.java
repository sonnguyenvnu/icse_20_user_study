public void scan(final String base,final Pattern pattern,final List<NutResource> list){
  final File baseFile=new File(root.getAbsolutePath() + "/" + base);
  if (baseFile.isFile()) {
    list.add(new FileResource(baseFile).setPriority(priority));
    return;
  }
  Disks.visitFile(baseFile,new Scans.ResourceFileVisitor(list,base,priority),new Scans.ResourceFileFilter(pattern));
}
