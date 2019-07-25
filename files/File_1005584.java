package com.limpoxe.fairy.manager.mapping;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import android.util.Base64;

import com.limpoxe.fairy.content.PluginDescriptor;
import com.limpoxe.fairy.core.FairyGlobal;
import com.limpoxe.fairy.util.LogUtil;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static com.limpoxe.fairy.manager.mapping.PluginStubBinding.buildDefaultAction;

public class StubServiceMappingProcessor implements StubMappingProcessor {
    /**
     * key:stub Service Name
     * value:plugin Service Name
     */
    private static HashMap<String, String> serviceMapping = new HashMap<String, String>();
    private static boolean isPoolInited = false;

    @Override
    public String bindStub(PluginDescriptor pluginDescriptor, String pluginServiceClassName) {
        if (StubExact.isExact(pluginServiceClassName, PluginDescriptor.SERVICE)) {
            return pluginServiceClassName;
        }

        initStubPool();

        Iterator<Map.Entry<String, String>> itr = serviceMapping.entrySet().iterator();

        String idleStubServiceName = null;

        while (itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            if (entry.getValue() == null) {
                if (idleStubServiceName == null) {
                    idleStubServiceName = entry.getKey();
                    //这里找到空闲的idleStubServiceName以�?�，还需继续�??历，用�?�检查是�?�pluginServiceClassName已�?绑定过了
                }
            } else if (pluginServiceClassName.equals(entry.getValue())) {
                //已�?绑定过，直接返回
                LogUtil.v("已�?绑定过", entry.getKey(), pluginServiceClassName);
                return entry.getKey();
            }
        }

        //没有绑定到StubService，而且还有空余的StubService，进行绑定
        if (idleStubServiceName != null) {
            LogUtil.v("添加绑定", idleStubServiceName, pluginServiceClassName);
            serviceMapping.put(idleStubServiceName, pluginServiceClassName);
            //对serviceMapping�?久化是因为如果service处于�?行状�?时app�?�生了crash，系统会自动�?��?之�?的service，此时�?�件映射信�?�查�?到的�?会�?次crash
            save(serviceMapping);
            return idleStubServiceName;
        }

        //绑定失败
        return null;
    }

    @Override
    public void unBindStub(String stubClassName, String pluginStubClass) {
        initStubPool();

        Iterator<Map.Entry<String, String>> itr = serviceMapping.entrySet().iterator();
        while (itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            if (pluginStubClass.equals(entry.getValue())) {
                //如果存在绑定关系，解绑
                LogUtil.v("回收绑定", entry.getKey(), entry.getValue());
                serviceMapping.put(entry.getKey(), null);
                save(serviceMapping);
                break;
            }
        }
    }

    @Override
    public boolean isStub(String stubClassName) {
        initStubPool();
        return serviceMapping.containsKey(stubClassName);
    }

    @Override
    public String getBindedPluginClassName(String stubServiceName) {
        if (StubExact.isExact(stubServiceName, PluginDescriptor.SERVICE)) {
            return stubServiceName;
        }

        initStubPool();

        Iterator<Map.Entry<String, String>> itr = serviceMapping.entrySet().iterator();

        while (itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();

            if (entry.getKey().equals(stubServiceName)) {
                return entry.getValue();
            }
        }

        //没有找到，�?试�?�?盘�?��?
        HashMap<String, String> mapping = restore();
        if (mapping != null) {
            itr = mapping.entrySet().iterator();
            while (itr.hasNext()) {
                Map.Entry<String, String> entry = itr.next();

                if (entry.getKey().equals(stubServiceName)) {
                    serviceMapping.put(stubServiceName, entry.getValue());
                    save(serviceMapping);
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    @Override
    public int getType() {
        return TYPE_SERVICE;
    }

    private static synchronized void loadStubService() {
        Intent launchModeIntent = new Intent();
        launchModeIntent.setAction(buildDefaultAction());
        launchModeIntent.setPackage(FairyGlobal.getHostApplication().getPackageName());

        List<ResolveInfo> list = FairyGlobal.getHostApplication().getPackageManager().queryIntentServices(launchModeIntent, PackageManager.MATCH_DEFAULT_ONLY);

        if (list != null && list.size() >0) {
            for (ResolveInfo resolveInfo:
                    list) {
                serviceMapping.put(resolveInfo.serviceInfo.name, null);
            }
            HashMap<String, String> mapping = restore();
            if (mapping != null) {
                serviceMapping.putAll(mapping);
            }
            //�?�有service需�?固化
            save(serviceMapping);
        }
    }

    private static boolean save(HashMap<String, String> mapping) {

        ObjectOutputStream objectOutputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(mapping);
            objectOutputStream.flush();

            byte[] data = byteArrayOutputStream.toByteArray();
            String list = Base64.encodeToString(data, Base64.DEFAULT);

            FairyGlobal.getHostApplication()
                    .getSharedPreferences("plugins.serviceMapping", Context.MODE_PRIVATE)
                    .edit().putString("plugins.serviceMapping.map", list).commit();

            return true;
        } catch (Exception e) {
            LogUtil.printException("StubServiceMappingProcessor.save", e);
        } finally {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException e) {
                    LogUtil.printException("StubServiceMappingProcessor.save", e);
                }
            }
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    LogUtil.printException("StubServiceMappingProcessor.save", e);
                }
            }
        }
        return false;
    }

    private static HashMap<String, String> restore() {
        String list = FairyGlobal.getHostApplication()
                .getSharedPreferences("plugins.serviceMapping", Context.MODE_PRIVATE)
                .getString("plugins.serviceMapping.map", "");
        Serializable object = null;
        if (!TextUtils.isEmpty(list)) {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                    Base64.decode(list, Base64.DEFAULT));
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                object = (Serializable) objectInputStream.readObject();
            } catch (Exception e) {
                LogUtil.printException("StubServiceMappingProcessor.restore", e);
            } finally {
                if (objectInputStream != null) {
                    try {
                        objectInputStream.close();
                    } catch (IOException e) {
                        LogUtil.printException("StubServiceMappingProcessor.restore", e);
                    }
                }
                if (byteArrayInputStream != null) {
                    try {
                        byteArrayInputStream.close();
                    } catch (IOException e) {
                        LogUtil.printException("StubServiceMappingProcessor.restore", e);
                    }
                }
            }
        }
        if (object != null) {

            HashMap<String, String> mapping = (HashMap<String, String>) object;
            return mapping;
        }
        return null;
    }

    private static void initStubPool() {

        if (isPoolInited) {
            return;
        }

        loadStubService();

        isPoolInited = true;
    }

}
