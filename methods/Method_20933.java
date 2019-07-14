public static @NonNull ProjectNotification disabled(){
  return enabled().toBuilder().email(false).mobile(false).build();
}
