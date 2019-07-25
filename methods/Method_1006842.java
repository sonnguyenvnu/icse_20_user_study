@PostConstruct public void initialize(){
  try {
    if (dataSource == null) {
      logger.warn("No datasource was provided...using a Map based JobRepository");
      if (getTransactionManager() == null) {
        logger.warn("No transaction manager was provided, using a ResourcelessTransactionManager");
        this.transactionManager=new ResourcelessTransactionManager();
      }
      MapJobRepositoryFactoryBean jobRepositoryFactory=new MapJobRepositoryFactoryBean(getTransactionManager());
      jobRepositoryFactory.afterPropertiesSet();
      this.jobRepository=jobRepositoryFactory.getObject();
      MapJobExplorerFactoryBean jobExplorerFactory=new MapJobExplorerFactoryBean(jobRepositoryFactory);
      jobExplorerFactory.afterPropertiesSet();
      this.jobExplorer=jobExplorerFactory.getObject();
    }
 else {
      this.jobRepository=createJobRepository();
      this.jobExplorer=createJobExplorer();
    }
    this.jobLauncher=createJobLauncher();
  }
 catch (  Exception e) {
    throw new BatchConfigurationException(e);
  }
}
