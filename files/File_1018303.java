/*
 *
 *  * Copyright (c) 2017.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.itfsw.mybatis.generator.plugins;

import com.itfsw.mybatis.generator.plugins.utils.BasePlugin;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.config.Context;
import org.mybatis.generator.config.JavaModelGeneratorConfiguration;

import java.util.List;
import java.util.Properties;

/**
 * ---------------------------------------------------------------------------
 * Example类生�?�?置修改
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/1/12 12:36
 * ---------------------------------------------------------------------------
 */
public class ExampleTargetPlugin extends BasePlugin {
    public static final String PRO_TARGET_PACKAGE = "targetPackage";  // �?置targetPackage�??
    private String targetPackage;   // 目标包

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean validate(List<String> warnings) {
        // 获�?��?置的目标package
        Properties properties = getProperties();
        this.targetPackage = properties.getProperty(PRO_TARGET_PACKAGE);
        if (this.targetPackage == null){
            warnings.add("请�?置com.itfsw.mybatis.generator.plugins.ExampleTargetPlugin�?�件的目标包�??(targetPackage)�?");
            return false;
        }
        return super.validate(warnings);
    }

    /**
     * �?始化阶段
     * 具体执行顺�? http://www.mybatis.org/generator/reference/pluggingIn.html
     *
     * @param introspectedTable
     */
    @Override
    public void initialized(IntrospectedTable introspectedTable) {
        super.initialized(introspectedTable);
        String exampleType = introspectedTable.getExampleType();
        // 修改包�??
        Context context = getContext();
        JavaModelGeneratorConfiguration configuration = context.getJavaModelGeneratorConfiguration();
        String targetPackage = configuration.getTargetPackage();
        String newExampleType = exampleType.replace(targetPackage, this.targetPackage);

        introspectedTable.setExampleType(newExampleType);

        logger.debug("itfsw(Example 目标包修改�?�件):修改"+introspectedTable.getExampleType()+"的包到"+this.targetPackage);
    }

}
