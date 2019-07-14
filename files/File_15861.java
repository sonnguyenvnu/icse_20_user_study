package org.hswebframework.web.commons.bean;

/**
 * 支�?验�?的bean
 *
 * @author zhouhao
 * @since 3.0
 */
public interface ValidateBean extends Bean {

    /**
     * �?试验�?此bean,如果验�?未通过,将抛出{@link org.hswebframework.web.validate.ValidationException}
     *
     * @param group 验�?分组
     * @param <T>   当�?对象类型
     * @return 当�?对象
     */
    default <T extends ValidateBean> T tryValidate(Class... group) {
        BeanValidator.tryValidate(this, group);
        return (T) this;
    }
}
