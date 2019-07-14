public QuoineOrdersList listQuoineOrders() throws IOException {
  try {
    return quoine.listOrders(QUOINE_API_VERSION,signatureCreator,contentType,"live");
  }
 catch (  HttpStatusIOException e) {
    throw handleHttpError(e);
  }
}
