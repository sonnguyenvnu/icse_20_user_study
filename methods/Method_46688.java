@Override public void deleteExceptions(List<Long> ids){
  for (  Long id : ids) {
    txExceptionRepository.deleteById(id);
  }
}
