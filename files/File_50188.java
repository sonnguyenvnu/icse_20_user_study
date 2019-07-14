package cn.finalteam.rxgalleryfinal.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.File;

/**
 * Desction:Media Bean
 * Author:pengjianbo  Dujinyang
 * Date:16/5/4 下�?�4:14
 */
public class MediaBean implements Parcelable {

    public static final Creator<MediaBean> CREATOR = new Creator<MediaBean>() {
        @Override
        public MediaBean createFromParcel(Parcel in) {
            return new MediaBean(in);
        }

        @Override
        public MediaBean[] newArray(int size) {
            return new MediaBean[size];
        }
    };
    //图片ID
    private long id;
    private String title;
    //图片�?视频�?地�?�
    private String originalPath;
    //图片�?视频创建时间
    private long createDate;
    //图片�?视频最�?�修改时间
    private long modifiedDate;
    //媒体类型
    private String mimeType;
    //宽
    private int width;
    //高
    private int height;
    //纬度
    private double latitude;
    //�?度
    private double longitude;
    //图片方�?�
    private int orientation;
    //文件大�?
    private long length;
    //文件夹相关
    private String bucketId;
    private String bucketDisplayName;
    //大缩略图
    private String thumbnailBigPath;
    //�?缩略图
    private String thumbnailSmallPath;

    public MediaBean() {
    }

    MediaBean(Parcel in) {
        id = in.readLong();
        title = in.readString();
        originalPath = in.readString();
        createDate = in.readLong();
        modifiedDate = in.readLong();
        mimeType = in.readString();
        bucketId = in.readString();
        bucketDisplayName = in.readString();
        thumbnailBigPath = in.readString();
        thumbnailSmallPath = in.readString();
        width = in.readInt();
        height = in.readInt();
        latitude = in.readDouble();
        longitude = in.readDouble();
        orientation = in.readInt();
        length = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(title);
        dest.writeString(originalPath);
        dest.writeLong(createDate);
        dest.writeLong(modifiedDate);
        dest.writeString(mimeType);
        dest.writeString(bucketId);
        dest.writeString(bucketDisplayName);
        dest.writeString(thumbnailBigPath);
        dest.writeString(thumbnailSmallPath);
        dest.writeInt(width);
        dest.writeInt(height);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeInt(orientation);
        dest.writeLong(length);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    public void setOriginalPath(String originalPath) {
        this.originalPath = originalPath;
    }

    public long getCreateDate() {
        return createDate;
    }

    public void setCreateDate(long createDate) {
        this.createDate = createDate;
    }

    public long getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(long modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getBucketId() {
        return bucketId;
    }

    public void setBucketId(String bucketId) {
        this.bucketId = bucketId;
    }

    public String getBucketDisplayName() {
        return bucketDisplayName;
    }

    public void setBucketDisplayName(String bucketDisplayName) {
        this.bucketDisplayName = bucketDisplayName;
    }

    public String getThumbnailBigPath() {
        if (new File(thumbnailBigPath).exists()) {
            return thumbnailBigPath;
        }
        return "";
    }

    public void setThumbnailBigPath(String thumbnailBigPath) {
        this.thumbnailBigPath = thumbnailBigPath;
    }

    public String getThumbnailSmallPath() {
        if (new File(thumbnailSmallPath).exists()) {
            return thumbnailSmallPath;
        }
        return "";
    }

    public void setThumbnailSmallPath(String thumbnailSmallPath) {
        this.thumbnailSmallPath = thumbnailSmallPath;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public int getOrientation() {
        return orientation;
    }

    public void setOrientation(int orientation) {
        this.orientation = orientation;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof MediaBean)) {
            return false;
        }

        MediaBean bean = (MediaBean) obj;
        return bean.getId() == getId();

    }

    @Override
    public String toString() {
        return "MediaBean{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", originalPath='" + originalPath + '\'' +
                ", createDate=" + createDate +
                ", modifiedDate=" + modifiedDate +
                ", mimeType='" + mimeType + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", orientation=" + orientation +
                ", length=" + length +
                ", bucketId='" + bucketId + '\'' +
                ", bucketDisplayName='" + bucketDisplayName + '\'' +
                ", thumbnailBigPath='" + thumbnailBigPath + '\'' +
                ", thumbnailSmallPath='" + thumbnailSmallPath + '\'' +
                '}';
    }
}
