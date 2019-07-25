package com.lqr.imagepicker;

import com.lqr.imagepicker.bean.ImageFolder;
import com.lqr.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

public class ImagePickStore {

    private ArrayList<ImageItem> pickedImages = new ArrayList<>();   //选中的图片集�?�
    private List<ImageFolder> imageFolders;      //所有的图片文件夹
    private int currentImageFolder = 0;  //当�?选中的文件夹�?置 0表示所有图片
    private boolean compress = true;

    public List<ImageFolder> getImageFolders() {
        return imageFolders;
    }

    private ImagePickStore() {

    }

    private static ImagePickStore store;

    public synchronized static ImagePickStore getInstance() {
        if (store == null) {
            store = new ImagePickStore();
        }
        return store;
    }


    public void setImageFolders(List<ImageFolder> imageFolders) {
        this.imageFolders = imageFolders;
    }

    public int getCurrentImageFolderPosition() {
        return currentImageFolder;
    }

    public void setCurrentImageFolderPosition(int mCurrentSelectedImageSetPosition) {
        currentImageFolder = mCurrentSelectedImageSetPosition;
    }

    public ArrayList<ImageItem> getCurrentImageFolderItems() {
        return imageFolders.get(currentImageFolder).images;
    }

    public boolean isSelect(ImageItem item) {
        return pickedImages.contains(item);
    }

    public int getSelectImageCount() {
        if (pickedImages == null) {
            return 0;
        }
        return pickedImages.size();
    }

    public ArrayList<ImageItem> getSelectedImages() {
        return pickedImages;
    }

    public void clearSelectedImages() {
        if (pickedImages != null) {
            pickedImages.clear();
        }
    }

    public boolean isCompress() {
        return compress;
    }

    public void setCompress(boolean compress) {
        this.compress = compress;
    }

    public void destroy() {
        store = null;
    }

    public void addSelectedImageItem(int position, ImageItem item, boolean isAdd) {
        if (isAdd) {
            pickedImages.add(item);
        } else {
            pickedImages.remove(item);
        }
    }
}
