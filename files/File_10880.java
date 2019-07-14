package com.vondear.rxtool;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vondear
 * @date 2018/5/2 15:00:00
 */
public class RxPackageManagerTool {

    public static List<PackageInfo> initPackageManager(Context mContext) {
        PackageManager mPackageManager = mContext.getPackageManager();
        return mPackageManager.getInstalledPackages(0);
    }

    /**
     * 包�??是�?�存在
     * @param mContext 实体
     * @param packageName 待检测 包�??
     * @return 结果
     */
    public static boolean haveExistPackageName(Context mContext,String packageName) {
        List<PackageInfo> packageInfos =  initPackageManager(mContext);
        List<String> mPackageNames = new ArrayList<>();
        if (packageInfos != null) {
            for (int i = 0; i < packageInfos.size(); i++) {
                mPackageNames.add(packageInfos.get(i).packageName);
            }
        }
        return mPackageNames.contains(packageName);
    }
}
