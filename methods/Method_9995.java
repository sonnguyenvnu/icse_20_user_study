/** 
 * Start all cron tasks.
 */
public void start(){
  long delay=10000;
  Symphonys.SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
    try {
      articleMgmtService.expireStick();
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Executes cron failed",e);
    }
 finally {
      Stopwatchs.release();
    }
  }
,delay,60 * 1000,TimeUnit.MILLISECONDS);
  delay+=2000;
  Symphonys.SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
    try {
      verifycodeMgmtService.sendEmailVerifycode();
      verifycodeMgmtService.removeExpiredVerifycodes();
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Executes cron failed",e);
    }
 finally {
      Stopwatchs.release();
    }
  }
,delay,5 * 1000,TimeUnit.MILLISECONDS);
  delay+=2000;
  Symphonys.SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
    try {
      cacheMgmtService.refreshCache();
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Executes cron failed",e);
    }
 finally {
      Stopwatchs.release();
    }
  }
,delay,30 * 60 * 1000,TimeUnit.MILLISECONDS);
  delay+=2000;
  Symphonys.SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
    try {
      invitecodeMgmtService.expireInvitecodes();
      mailMgmtService.sendWeeklyNewsletter();
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Executes cron failed",e);
    }
 finally {
      Stopwatchs.release();
    }
  }
,delay,5 * 60 * 1000,TimeUnit.MILLISECONDS);
  delay+=2000;
  Symphonys.SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(() -> {
    try {
      userMgmtService.resetUnverifiedUsers();
    }
 catch (    final Exception e) {
      LOGGER.log(Level.ERROR,"Executes cron failed",e);
    }
 finally {
      Stopwatchs.release();
    }
  }
,delay,2 * 60 * 60 * 1000,TimeUnit.MILLISECONDS);
  delay+=2000;
}
