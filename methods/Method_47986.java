public void onRepairDB(){
  taskRunner.execute(() -> {
    habitList.repair();
    screen.showMessage(Message.DATABASE_REPAIRED);
  }
);
}
