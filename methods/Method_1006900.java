@Override public void add(StepExecution stepExecution){
  validateStepExecution(stepExecution);
  stepExecution.setLastUpdated(new Date(System.currentTimeMillis()));
  stepExecutionDao.saveStepExecution(stepExecution);
  ecDao.saveExecutionContext(stepExecution);
}
