public static RandomAccessFile acquire(File file) throws FileNotFoundException {
  return canonicalRafs.compute(file,(f,ref) -> {
    if (ref == null) {
      try {
        return new RafReference(new RandomAccessFile(f,"rw"));
      }
 catch (      FileNotFoundException e) {
        throw Jvm.rethrow(e);
      }
    }
 else {
      ref.refCount++;
      return ref;
    }
  }
).raf;
}
