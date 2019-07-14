@SuppressWarnings("unchecked") private static <T extends Throwable>void throwsUnchecked(Throwable toThrow) throws T {
  throw (T)toThrow;
}
