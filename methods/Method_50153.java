static Set<FileDescriptor> extractDependencies(List<FileDescriptor> fileDescriptors){
  LinkedList<FileDescriptor> loop=new LinkedList<>(fileDescriptors);
  HashSet<FileDescriptor> fileDescriptorSet=new HashSet<>(fileDescriptors);
  while (!loop.isEmpty()) {
    FileDescriptor fileDescriptor=loop.pop();
    for (    FileDescriptor dependency : fileDescriptor.getDependencies()) {
      if (!fileDescriptorSet.contains(dependency)) {
        fileDescriptorSet.add(dependency);
        loop.push(dependency);
      }
    }
  }
  return ImmutableSet.copyOf(fileDescriptorSet);
}
