package cn.iocoder.common.framework.validator;

import cn.iocoder.common.framework.core.IntArrayValuable;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class InEnumValidator implements ConstraintValidator<InEnum, Integer> {

    private List<Integer> values;

    @Override
    public void initialize(InEnum annotation) {
        IntArrayValuable[] values = annotation.value().getEnumConstants();
        if (values.length == 0) {
            this.values = Collections.emptyList();
        } else {
            this.values = Arrays.stream(values[0].array()).boxed().collect(Collectors.toList());
        }
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 校验通过
        if (values.contains(value)) {
            return true;
        }
        // 校验�?通过，自定义�??示语�?�（因为，注解上的 value 是枚举类，无法获得枚举类的实际值）
        context.disableDefaultConstraintViolation(); // �?用默认的 message 的值
        context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate()
                .replaceAll("\\{value}", values.toString())).addConstraintViolation(); // �?新添加错误�??示语�?�
        return false;
    }

}
