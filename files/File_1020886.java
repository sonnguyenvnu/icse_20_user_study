package net.csdn.jpa.enhancer;

import javassist.CtClass;
import javassist.CtMethod;
import javassist.bytecode.AnnotationsAttribute;
import net.csdn.annotation.callback.*;
import net.csdn.common.enhancer.EnhancerHelper;
import net.csdn.common.settings.Settings;
import net.csdn.enhancer.BitEnhancer;
import net.csdn.enhancer.association.ManyToManyEnhancer;
import net.csdn.enhancer.association.ManyToOneEnhancer;
import net.csdn.enhancer.association.OneToManyEnhancer;
import net.csdn.enhancer.association.OneToOneEnhancer;

import javax.persistence.*;
import java.util.List;
import java.util.Map;

import static net.csdn.common.collections.WowCollections.map;
import static net.csdn.common.logging.support.MessageFormat.format;


/**
 * User: WilliamZhu
 * Date: 12-7-4
 * Time: 下�?�9:08
 */
public class AssociationEnhancer implements BitEnhancer {
    private Settings settings;

    public AssociationEnhancer(Settings settings) {
        this.settings = settings;
    }

    @Override
    public void enhance(List<ModelClass> modelClasses) throws Exception {
        for (ModelClass modelClass : modelClasses) {
            inner_enhance(modelClass);
        }

    }


    /*
        Hibernate 的关�?�关系太�?�?�了。�?么你区分控制端和被控制端。�?么你必须在使用的时候将两端都设置好关�?�关系。
        对于mappedBy也是一个无语的设计。为什么我�?通过它�?�区分控制端？
     */
    public void inner_enhance(ModelClass modelClass) throws Exception {

        CtClass ctClass = modelClass.originClass;
        enhanceJPACallback(ctClass);

        OneToOneEnhancer oneToOneEnhancer = new OneToOneEnhancer(modelClass);
        oneToOneEnhancer.enhancer();

        OneToManyEnhancer oneToManyEnhancer = new OneToManyEnhancer(modelClass);
        oneToManyEnhancer.enhancer();

        ManyToOneEnhancer manyToOneEnhancer = new ManyToOneEnhancer(modelClass);
        manyToOneEnhancer.enhancer();

        ManyToManyEnhancer manyToManyEnhancer = new ManyToManyEnhancer(modelClass);
        manyToManyEnhancer.enhancer();


        ctClass.defrost();
    }

    private Map<Class, Class> callback_classes = map(
            AfterSave.class, PostPersist.class,
            BeforeSave.class, PrePersist.class,
            BeforeUpdate.class, PreUpdate.class,
            AfterUpdate.class, PostUpdate.class,
            BeforeDestroy.class, PostRemove.class,
            AfterLoad.class, PostLoad.class
    );

    private void enhanceJPACallback(CtClass ctClass) throws Exception {
        CtMethod[] methods = ctClass.getDeclaredMethods();
        for (CtMethod ctMethod : methods) {
            if (ctMethod.hasAnnotation(AfterSave.class)) {
                enhanceJPACallback(ctClass, ctMethod, AfterSave.class);
            }
            if (ctMethod.hasAnnotation(BeforeSave.class)) {
                enhanceJPACallback(ctClass, ctMethod, BeforeSave.class);
            }
            if (ctMethod.hasAnnotation(AfterUpdate.class)) {
                enhanceJPACallback(ctClass, ctMethod, AfterUpdate.class);
            }
            if (ctMethod.hasAnnotation(BeforeUpdate.class)) {
                enhanceJPACallback(ctClass, ctMethod, BeforeUpdate.class);
            }
            if (ctMethod.hasAnnotation(BeforeDestroy.class)) {
                enhanceJPACallback(ctClass, ctMethod, BeforeDestroy.class);
            }
            if (ctMethod.hasAnnotation(AfterLoad.class)) {
                enhanceJPACallback(ctClass, ctMethod, AfterLoad.class);
            }
        }
    }

    private void enhanceJPACallback(CtClass ctClass, CtMethod method, Class anno) throws Exception {
        if (method.hasAnnotation(anno)) {
            CtMethod ctMethod = CtMethod.make(format("public void {}() {\n" +
                    "        net.csdn.jpa.context.JPAContext jpaContext = getJPAConfig().reInitJPAContext();\n" +
                    "        try {\n" +
                    "            {}();\n" +
                    "            getJPAConfig().getJPAContext().closeTx(false);\n" +
                    "        } catch (Exception e) {\n" +
                    "            getJPAConfig().getJPAContext().closeTx(true);\n" +
                    "        } finally {\n" +
                    "            getJPAConfig().setJPAContext(jpaContext);\n" +
                    "        }\n" +
                    "    }", "$_" + method.getName(), method.getName()), ctClass);

            ctClass.addMethod(ctMethod);
            AnnotationsAttribute annotationsAttribute = EnhancerHelper.getAnnotations(ctMethod);
            EnhancerHelper.createAnnotation(annotationsAttribute, callback_classes.get(anno));
        }
    }


}
