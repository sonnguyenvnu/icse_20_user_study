private boolean removeLastPageFromStack(){
  if (pagesStack.size() < 2) {
    return false;
  }
  pagesStack.remove(pagesStack.size() - 1);
  currentPage=pagesStack.get(pagesStack.size() - 1);
  updateInterfaceForCurrentPage(-1);
  return true;
}
