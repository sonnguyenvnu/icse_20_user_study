protected void updateCategoryChooser(){
  if (categoryChooser != null) {
    ArrayList<String> categories;
    categoryChooser.removeAllItems();
    categories=new ArrayList<String>(contribListing.getCategories(filter));
    Collections.sort(categories);
    boolean categoriesFound=false;
    categoryChooser.addItem(ManagerFrame.ANY_CATEGORY);
    for (    String s : categories) {
      categoryChooser.addItem(s);
      if (!s.equals(Contribution.UNKNOWN_CATEGORY)) {
        categoriesFound=true;
      }
    }
    categoryChooser.setVisible(categoriesFound);
  }
}
