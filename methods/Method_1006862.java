/** 
 * Build a flow and inject it into the parent builder. The parent builder is then returned so it can be enhanced before building an actual job.  Normally called explicitly via  {@link #end()}.
 * @see org.springframework.batch.core.job.builder.FlowBuilder#build()
 */
@Override public FlowJobBuilder build(){
  Flow flow=flow();
  if (flow instanceof InitializingBean) {
    try {
      ((InitializingBean)flow).afterPropertiesSet();
    }
 catch (    Exception e) {
      throw new FlowBuilderException(e);
    }
  }
  parent.flow(flow);
  return parent;
}
