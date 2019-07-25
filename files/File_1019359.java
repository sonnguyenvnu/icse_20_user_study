package cn.iocoder.mall.admin.sdk.context;

/**
 * {@link AdminSecurityContext} Holder
 *
 * �?�考 spring security 的 ThreadLocalSecurityContextHolderStrategy 类，简�?�实现。
 */
public class AdminSecurityContextHolder {

    private static final ThreadLocal<AdminSecurityContext> SECURITY_CONTEXT = new ThreadLocal<>();

    public static void setContext(AdminSecurityContext context) {
        SECURITY_CONTEXT.set(context);
    }

    public static AdminSecurityContext getContext() {
        AdminSecurityContext ctx = SECURITY_CONTEXT.get();
        // 为空时，设置一个空的进去
        if (ctx == null) {
            ctx = new AdminSecurityContext();
            SECURITY_CONTEXT.set(ctx);
        }
        return ctx;
    }

    public static void clear() {
        SECURITY_CONTEXT.remove();
    }

}
