package com.machines.vending.domain.commands.user;

import com.machines.vending.domain.exceptions.role.InvalidRoleException;
import com.machines.vending.domain.exceptions.user.InvalidPasswordException;
import com.machines.vending.domain.exceptions.user.InvalidUsernameException;
import com.machines.vending.domain.models.User;

public interface UpdateUserCommand {
    void execute(User user) throws InvalidPasswordException, InvalidUsernameException, InvalidRoleException;
}
