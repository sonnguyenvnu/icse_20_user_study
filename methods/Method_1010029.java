/** 
 * If  {@link IgnoreSettings#hideIgnoredFiles} is set to <code>true</code>, checks if specificnodes are ignored and filters them out.
 * @param parent   the parent node
 * @param children the list of child nodes according to the default project structure
 * @param settings the current project view settings
 * @return the modified collection of child nodes
 */
@NotNull @Override public Collection<AbstractTreeNode> modify(@NotNull AbstractTreeNode parent,@NotNull Collection<AbstractTreeNode> children,@Nullable ViewSettings settings){
  if (!ignoreSettings.isHideIgnoredFiles() || children.isEmpty()) {
    return children;
  }
  return ContainerUtil.filter(children,node -> {
    if (node instanceof BasePsiNode) {
      final VirtualFile file=((BasePsiNode)node).getVirtualFile();
      return file != null && (!ignoreManager.isFileIgnored(file) || ignoreManager.isFileTracked(file));
    }
    return true;
  }
);
}
