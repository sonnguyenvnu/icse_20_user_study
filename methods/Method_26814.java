@GetMapping("/global-exception") public String businessException() throws BusinessException {
  throw new BusinessException();
}
