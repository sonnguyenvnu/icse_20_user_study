/** 
 * ????Tracer
 * @return ????
 */
public static boolean isEnable(){
  boolean enable="sofaTracer".equals(RpcConfigs.getStringValue(RpcOptions.DEFAULT_TRACER));
  if (enable) {
    try {
      ClassUtils.forName("javax.ws.rs.container.ContainerRequestFilter");
      ClassUtils.forName("javax.ws.rs.container.ContainerResponseFilter");
      ClassUtils.forName("org.jboss.resteasy.core.interception.PostMatchContainerRequestContext");
      ClassUtils.forName("org.jboss.resteasy.plugins.server.netty.NettyHttpRequest");
      ClassUtils.forName("org.jboss.resteasy.plugins.server.netty.NettyHttpResponse");
    }
 catch (    Exception e) {
      return false;
    }
  }
  return enable;
}
