@Override public synchronized void regist(String subject,String group,RegistParam param){
  String env;
  String subEnv;
  if (envProvider != null && !Strings.isNullOrEmpty(env=envProvider.env(subject))) {
    subEnv=envProvider.subEnv(env);
    final String realGroup=toSubEnvIsolationGroup(group,env,subEnv);
    LOG.info("enable subenv isolation for {}/{}, rename consumer group to {}",subject,group,realGroup);
    group=realGroup;
    param.addFilter(new SubEnvIsolationPullFilter(env,subEnv));
  }
  registPullEntry(subject,group,param,new AlwaysPullStrategy());
  if (RetrySubjectUtils.isDeadRetrySubject(subject))   return;
  registPullEntry(RetrySubjectUtils.buildRetrySubject(subject,group),group,param,new WeightPullStrategy());
}
