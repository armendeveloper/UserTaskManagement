package com.armen.usertaskmanagement.propertyeditors;

import com.armen.usertaskmanagement.model.User;
import com.armen.usertaskmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.beans.PropertyEditorSupport;

/**
 * Created by armennalbandyan on 29/03/2016.
 */
public class UserEditor extends PropertyEditorSupport {

    @Autowired
    @Qualifier(value="userService")
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Converts given text username into User
     * @param text
     */
    @Override
    public void setAsText(String text) {
        User user = this.userService.getUserByUsername(text);
        this.setValue(user);
    }

    /**
     * Converts given User int string containing username
     * @return
     */
    @Override
    public String getAsText() {
        User user = null;
        if(this.getValue() != null){
            user = (User) this.getValue();
            return user.getUsername();
        }
        return null;

    }
}
