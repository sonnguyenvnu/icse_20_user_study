public static ExecThreadBinding require(){
  ExecThreadBinding execThreadBinding=STORAGE.get();
  if (execThreadBinding == null) {
    throw new UnmanagedThreadException();
  }
 else {
    return execThreadBinding;
  }
}
