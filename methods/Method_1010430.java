public void install(@NotNull IMakeService makeService){
  assert myActiveMakeService == null;
  myActiveMakeService=makeService;
  IMakeService.INSTANCE.set(makeService);
}
