@Around("execution(* org.springframework.cloud.client.serviceregistry.Registration.getPort())") public Object getPort(ProceedingJoinPoint pjp) throws Throwable {
  return serverPort != null ? serverPort : pjp.proceed();
}
