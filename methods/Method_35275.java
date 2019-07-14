void onModelRowClick(DemoModel model){
switch (model) {
case RX_LIFECYCLE:
    getRouter().pushController(RouterTransaction.with(new RxLifecycleController()).pushChangeHandler(new FadeChangeHandler()).popChangeHandler(new FadeChangeHandler()));
  break;
case RX_LIFECYCLE_2:
getRouter().pushController(RouterTransaction.with(new RxLifecycle2Controller()).pushChangeHandler(new FadeChangeHandler()).popChangeHandler(new FadeChangeHandler()));
break;
case AUTODISPOSE:
getRouter().pushController(RouterTransaction.with(new AutodisposeController()).pushChangeHandler(new FadeChangeHandler()).popChangeHandler(new FadeChangeHandler()));
break;
case ARCH_LIFECYCLE:
getRouter().pushController(RouterTransaction.with(new ArchLifecycleController()).pushChangeHandler(new FadeChangeHandler()).popChangeHandler(new FadeChangeHandler()));
break;
}
}
