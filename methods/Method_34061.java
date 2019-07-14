public boolean hasError(ClientHttpResponse response) throws IOException {
  return HttpStatus.Series.CLIENT_ERROR.equals(response.getStatusCode().series()) || this.errorHandler.hasError(response);
}
