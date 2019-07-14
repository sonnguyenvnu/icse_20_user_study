package org.hswebframework.web.authorization.twofactor.defaults;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hswebframework.web.authorization.twofactor.TwoFactorValidator;

/**
 * @author zhouhao
 * @since 3.0.4
 */
@AllArgsConstructor
public class UnsupportedTwoFactorValidator implements TwoFactorValidator {

    @Getter
    private String provider;

    @Override
    public boolean verify(String code, long timeout) {
        throw new UnsupportedOperationException("ä¸?æ”¯æŒ?çš„éªŒè¯?è§„åˆ™:" + provider);
    }

    @Override
    public boolean expired() {
        throw new UnsupportedOperationException("ä¸?æ”¯æŒ?çš„éªŒè¯?è§„åˆ™:" + provider);
    }
}
