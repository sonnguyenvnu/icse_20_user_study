public void filter(Filter filter) throws NoTestsRemainException {
  childrenLock.lock();
  try {
    List<T> children=new ArrayList<T>(getFilteredChildren());
    for (Iterator<T> iter=children.iterator(); iter.hasNext(); ) {
      T each=iter.next();
      if (shouldRun(filter,each)) {
        try {
          filter.apply(each);
        }
 catch (        NoTestsRemainException e) {
          iter.remove();
        }
      }
 else {
        iter.remove();
      }
    }
    filteredChildren=Collections.unmodifiableList(children);
    if (filteredChildren.isEmpty()) {
      throw new NoTestsRemainException();
    }
  }
  finally {
    childrenLock.unlock();
  }
}
