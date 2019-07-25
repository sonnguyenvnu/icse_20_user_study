public void write(UUID serviceUUID,UUID characteristicUUID,byte[] data,Integer maxByteSize,Integer queueSleepTime,Callback callback,int writeType){
  if (!isConnected()) {
    callback.invoke("Device is not connected",null);
    return;
  }
  if (gatt == null) {
    callback.invoke("BluetoothGatt is null");
  }
 else {
    BluetoothGattService service=gatt.getService(serviceUUID);
    BluetoothGattCharacteristic characteristic=findWritableCharacteristic(service,characteristicUUID,writeType);
    if (characteristic == null) {
      callback.invoke("Characteristic " + characteristicUUID + " not found.");
    }
 else {
      characteristic.setWriteType(writeType);
      if (writeQueue.size() > 0) {
        callback.invoke("You have already an queued message");
      }
      if (writeCallback != null) {
        callback.invoke("You're already writing");
      }
      if (writeQueue.size() == 0 && writeCallback == null) {
        if (BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT == writeType) {
          writeCallback=callback;
        }
        if (data.length > maxByteSize) {
          int dataLength=data.length;
          int count=0;
          byte[] firstMessage=null;
          List<byte[]> splittedMessage=new ArrayList<>();
          while (count < dataLength && (dataLength - count > maxByteSize)) {
            if (count == 0) {
              firstMessage=Arrays.copyOfRange(data,count,count + maxByteSize);
            }
 else {
              byte[] splitMessage=Arrays.copyOfRange(data,count,count + maxByteSize);
              splittedMessage.add(splitMessage);
            }
            count+=maxByteSize;
          }
          if (count < dataLength) {
            byte[] splitMessage=Arrays.copyOfRange(data,count,data.length);
            splittedMessage.add(splitMessage);
          }
          if (BluetoothGattCharacteristic.WRITE_TYPE_DEFAULT == writeType) {
            writeQueue.addAll(splittedMessage);
            if (!doWrite(characteristic,firstMessage)) {
              writeQueue.clear();
              writeCallback=null;
              callback.invoke("Write failed");
            }
          }
 else {
            try {
              boolean writeError=false;
              if (!doWrite(characteristic,firstMessage)) {
                writeError=true;
                callback.invoke("Write failed");
              }
              if (!writeError) {
                Thread.sleep(queueSleepTime);
                for (                byte[] message : splittedMessage) {
                  if (!doWrite(characteristic,message)) {
                    writeError=true;
                    callback.invoke("Write failed");
                    break;
                  }
                  Thread.sleep(queueSleepTime);
                }
                if (!writeError) {
                  callback.invoke();
                }
              }
            }
 catch (            InterruptedException e) {
              callback.invoke("Error during writing");
            }
          }
        }
 else         if (doWrite(characteristic,data)) {
          Log.d(BleManager.LOG_TAG,"Write completed");
          if (BluetoothGattCharacteristic.WRITE_TYPE_NO_RESPONSE == writeType) {
            callback.invoke();
          }
        }
 else {
          callback.invoke("Write failed");
          writeCallback=null;
        }
      }
    }
  }
}
