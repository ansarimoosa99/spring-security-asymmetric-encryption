package com.ansari.app.user;

import ch.qos.logback.core.util.StringUtil;
import com.ansari.app.user.request.ProfileUpdateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public void mergeUserInfo(final User user, final ProfileUpdateRequest request) {
        if(StringUtils.isNotBlank(request.getFirstName())
                && !user.getFirstName().equals(request.getFirstName())){
            user.setFirstName(request.getFirstName());
        }

        if(StringUtils.isNotBlank(request.getLastName())
                && !user.getLastName().equals(request.getLastName())){
            user.setLastName(request.getLastName());
        }

        if(request.getDateOfBirth() != null
                && !user.getDateOfBirth().equals(request.getDateOfBirth())){
            user.setDateOfBirth(request.getDateOfBirth());
        }
    }
}
