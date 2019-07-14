public void initCamera(final Runnable onInitRunnable){
  if (onInitRunnable != null && !onFinishCameraInitRunnables.contains(onInitRunnable)) {
    onFinishCameraInitRunnables.add(onInitRunnable);
  }
  if (loadingCameras || cameraInitied) {
    return;
  }
  loadingCameras=true;
  threadPool.execute(() -> {
    try {
      if (cameraInfos == null) {
        SharedPreferences preferences=MessagesController.getGlobalMainSettings();
        String cache=preferences.getString("cameraCache",null);
        Comparator<Size> comparator=(o1,o2) -> {
          if (o1.mWidth < o2.mWidth) {
            return 1;
          }
 else           if (o1.mWidth > o2.mWidth) {
            return -1;
          }
 else {
            if (o1.mHeight < o2.mHeight) {
              return 1;
            }
 else             if (o1.mHeight > o2.mHeight) {
              return -1;
            }
            return 0;
          }
        }
;
        ArrayList<CameraInfo> result=new ArrayList<>();
        if (cache != null) {
          SerializedData serializedData=new SerializedData(Base64.decode(cache,Base64.DEFAULT));
          int count=serializedData.readInt32(false);
          for (int a=0; a < count; a++) {
            CameraInfo cameraInfo=new CameraInfo(serializedData.readInt32(false),serializedData.readInt32(false));
            int pCount=serializedData.readInt32(false);
            for (int b=0; b < pCount; b++) {
              cameraInfo.previewSizes.add(new Size(serializedData.readInt32(false),serializedData.readInt32(false)));
            }
            pCount=serializedData.readInt32(false);
            for (int b=0; b < pCount; b++) {
              cameraInfo.pictureSizes.add(new Size(serializedData.readInt32(false),serializedData.readInt32(false)));
            }
            result.add(cameraInfo);
            Collections.sort(cameraInfo.previewSizes,comparator);
            Collections.sort(cameraInfo.pictureSizes,comparator);
          }
          serializedData.cleanup();
        }
 else {
          int count=Camera.getNumberOfCameras();
          Camera.CameraInfo info=new Camera.CameraInfo();
          int bufferSize=4;
          for (int cameraId=0; cameraId < count; cameraId++) {
            Camera.getCameraInfo(cameraId,info);
            CameraInfo cameraInfo=new CameraInfo(cameraId,info.facing);
            if (ApplicationLoader.mainInterfacePaused && ApplicationLoader.externalInterfacePaused) {
              throw new RuntimeException("app paused");
            }
            Camera camera=Camera.open(cameraInfo.getCameraId());
            Camera.Parameters params=camera.getParameters();
            List<Camera.Size> list=params.getSupportedPreviewSizes();
            for (int a=0; a < list.size(); a++) {
              Camera.Size size=list.get(a);
              if (size.width == 1280 && size.height != 720) {
                continue;
              }
              if (size.height < 2160 && size.width < 2160) {
                cameraInfo.previewSizes.add(new Size(size.width,size.height));
                if (BuildVars.LOGS_ENABLED) {
                  FileLog.d("preview size = " + size.width + " " + size.height);
                }
              }
            }
            list=params.getSupportedPictureSizes();
            for (int a=0; a < list.size(); a++) {
              Camera.Size size=list.get(a);
              if (size.width == 1280 && size.height != 720) {
                continue;
              }
              if (!"samsung".equals(Build.MANUFACTURER) || !"jflteuc".equals(Build.PRODUCT) || size.width < 2048) {
                cameraInfo.pictureSizes.add(new Size(size.width,size.height));
                if (BuildVars.LOGS_ENABLED) {
                  FileLog.d("picture size = " + size.width + " " + size.height);
                }
              }
            }
            camera.release();
            result.add(cameraInfo);
            Collections.sort(cameraInfo.previewSizes,comparator);
            Collections.sort(cameraInfo.pictureSizes,comparator);
            bufferSize+=4 + 4 + 8 * (cameraInfo.previewSizes.size() + cameraInfo.pictureSizes.size());
          }
          SerializedData serializedData=new SerializedData(bufferSize);
          serializedData.writeInt32(result.size());
          for (int a=0; a < count; a++) {
            CameraInfo cameraInfo=result.get(a);
            serializedData.writeInt32(cameraInfo.cameraId);
            serializedData.writeInt32(cameraInfo.frontCamera);
            int pCount=cameraInfo.previewSizes.size();
            serializedData.writeInt32(pCount);
            for (int b=0; b < pCount; b++) {
              Size size=cameraInfo.previewSizes.get(b);
              serializedData.writeInt32(size.mWidth);
              serializedData.writeInt32(size.mHeight);
            }
            pCount=cameraInfo.pictureSizes.size();
            serializedData.writeInt32(pCount);
            for (int b=0; b < pCount; b++) {
              Size size=cameraInfo.pictureSizes.get(b);
              serializedData.writeInt32(size.mWidth);
              serializedData.writeInt32(size.mHeight);
            }
          }
          preferences.edit().putString("cameraCache",Base64.encodeToString(serializedData.toByteArray(),Base64.DEFAULT)).commit();
          serializedData.cleanup();
        }
        cameraInfos=result;
      }
      AndroidUtilities.runOnUIThread(() -> {
        loadingCameras=false;
        cameraInitied=true;
        if (!onFinishCameraInitRunnables.isEmpty()) {
          for (int a=0; a < onFinishCameraInitRunnables.size(); a++) {
            onFinishCameraInitRunnables.get(a).run();
          }
          onFinishCameraInitRunnables.clear();
        }
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.cameraInitied);
      }
);
    }
 catch (    Exception e) {
      AndroidUtilities.runOnUIThread(() -> {
        onFinishCameraInitRunnables.clear();
        loadingCameras=false;
        cameraInitied=false;
      }
);
    }
  }
);
}
