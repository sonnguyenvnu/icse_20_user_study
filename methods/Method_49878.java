private static void checkMethod(String method) throws MmsHttpException {
  if (!METHOD_GET.equals(method) && !METHOD_POST.equals(method)) {
    throw new MmsHttpException(0,"Invalid method " + method);
  }
}
