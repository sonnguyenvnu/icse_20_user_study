/** 
 * Finds for  {@link VirtualFile} list using glob rule in given root directory.
 * @param root          root directory
 * @param entries       ignore entries
 * @param includeNested attach children to the search result
 * @return search result
 */
@NotNull public static Map<IgnoreEntry,List<VirtualFile>> find(@NotNull final VirtualFile root,@NotNull List<IgnoreEntry> entries,@NotNull final MatcherUtil matcher,final boolean includeNested){
  final ConcurrentMap<IgnoreEntry,List<VirtualFile>> result=ContainerUtil.newConcurrentMap();
  final HashMap<IgnoreEntry,Pattern> map=ContainerUtil.newHashMap();
  for (  IgnoreEntry entry : entries) {
    result.put(entry,ContainerUtil.newArrayList());
    final Pattern pattern=createPattern(entry);
    if (pattern != null) {
      map.put(entry,pattern);
    }
  }
  VirtualFileVisitor<HashMap<IgnoreEntry,Pattern>> visitor=new VirtualFileVisitor<HashMap<IgnoreEntry,Pattern>>(VirtualFileVisitor.NO_FOLLOW_SYMLINKS){
    @Override public boolean visitFile(    @NotNull VirtualFile file){
      if (root.equals(file)) {
        return true;
      }
      final HashMap<IgnoreEntry,Pattern> current=ContainerUtil.newHashMap();
      if (getCurrentValue().isEmpty()) {
        return false;
      }
      final String path=Utils.getRelativePath(root,file);
      if (path == null || Utils.isVcsDirectory(file)) {
        return false;
      }
      for (      Map.Entry<IgnoreEntry,Pattern> item : getCurrentValue().entrySet()) {
        final Pattern value=item.getValue();
        boolean matches=false;
        if (value == null || matcher.match(value,path)) {
          matches=true;
          result.get(item.getKey()).add(file);
        }
        if (includeNested && matches) {
          current.put(item.getKey(),null);
        }
 else {
          current.put(item.getKey(),item.getValue());
        }
      }
      setValueForChildren(current);
      return true;
    }
  }
;
  visitor.setValueForChildren(map);
  VfsUtil.visitChildrenRecursively(root,visitor);
  return result;
}
