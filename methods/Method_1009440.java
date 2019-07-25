@Provides @Singleton public static CrashReportExceptionHandler serializer(Context ctx){
  return new CrashReportExceptionHandler(ctx);
}
