public void create(Category category) throws ServiceException {
  super.create(category);
  StringBuilder lineage=new StringBuilder();
  Category parent=category.getParent();
  if (parent != null && parent.getId() != null && parent.getId() != 0) {
    lineage.append(parent.getLineage()).append("/").append(parent.getId());
    category.setDepth(parent.getDepth() + 1);
  }
 else {
    lineage.append("/");
    category.setDepth(0);
  }
  category.setLineage(lineage.toString());
  super.update(category);
}
