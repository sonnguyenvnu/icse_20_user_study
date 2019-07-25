private int insert(CheckedOutBook checkedoutBook){
  return insert(checkedoutBook.getBookId(),checkedoutBook.type(),CheckedOut,null,null,null,null,checkedoutBook.getCheckedOutAt().getLibraryBranchId(),checkedoutBook.getByPatron().getPatronId());
}
