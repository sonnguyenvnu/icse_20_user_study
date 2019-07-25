public static __ptr__ memalloc(__ptr__ heap,size_t request){
  ENTERING("9mtjrx0vjzwuecjwpxylr9tag","memalloc");
  try {
    __ptr__ rv;
    rv=(__ptr__)request.malloc();
    return rv;
  }
  finally {
    LEAVING("9mtjrx0vjzwuecjwpxylr9tag","memalloc");
  }
}
